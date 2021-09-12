package com.dangtuan.order.service.impl;

import com.dangtuan.dto.delivery.DeliveryDto;
import com.dangtuan.dto.order.OrderDeliveryDto;
import com.dangtuan.dto.order.OrderDto;
import com.dangtuan.kafka.KafkaEventType;
import com.dangtuan.kafka.KafkaMessage;
import com.dangtuan.order.constants.ApplicationConstants;
import com.dangtuan.order.dto.UserSession;
import com.dangtuan.order.entity.Order;
import com.dangtuan.order.exception.OrderNotFoundException;
import com.dangtuan.order.kafka.producer.MessageProducer;
import com.dangtuan.order.mapper.OrderMapper;
import com.dangtuan.order.repository.OrderRepository;
import com.dangtuan.order.service.OrderService;
import com.dangtuan.order.util.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Optional;
import javax.inject.Provider;
import javax.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
@Log4j2
public class OrderServiceImpl implements OrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  Provider<UserSession> userSessionProvider;

  @Autowired
  private MessageProducer messageProducer;

  @Autowired
  private RestTemplate restTemplate;

  @Value("${api.payment}")
  private String PAYMENT_URL;

  @Value("${api.enable}")
  private String ENABLE_PAYMENT;

  @Override
  public OrderDto getOrder(final Long id) throws OrderNotFoundException {
    final Optional<Order> orderOptional = this.orderRepository.findById(id);
    if (orderOptional.isPresent()) {
      final OrderDto orderDto = OrderMapper.INSTANCE.mapToOrderDto(orderOptional.get());
      return orderDto;
    }
    throw new OrderNotFoundException(ApplicationConstants.ORDER_NOT_FOUND_EXCEPTION);
  }

  @Override
  public OrderDto createOrder(final OrderDto orderDto) {
    final UserSession userSession = userSessionProvider.get();
    final Order order = OrderMapper.INSTANCE.mapToOrder(orderDto);
    order.setTenantId(userSession.getTenantId());
    this.orderRepository.save(order);
    final HttpEntity<Object> entity = new HttpEntity<>(Utils.getHeaders());
    if (Boolean.TRUE.equals(ENABLE_PAYMENT)) {
      final ResponseEntity<HashMap> response = this.restTemplate
              .exchange(PAYMENT_URL, HttpMethod.POST, entity, HashMap.class);
    }
    return OrderMapper.INSTANCE.mapToOrderDto(order);
  }

  @Override
  public OrderDto updateOrder(final OrderDto orderDto, final Long id) {
    final Optional<Order> orderOptional = this.orderRepository.findById(id);
    final UserSession userSession = userSessionProvider.get();
    if (orderOptional.isPresent()) {
      final Order order = orderOptional.get();
      OrderMapper.INSTANCE.mapToUpdate(orderDto, order);
      order.setTenantId(userSession.getTenantId());
      this.orderRepository.save(order);
      return orderDto;
    }
    throw new OrderNotFoundException(ApplicationConstants.ORDER_NOT_FOUND_EXCEPTION);
  }

  @Override
  public OrderDto updateOrderDelivery(final DeliveryDto deliveryDto) {
    final Optional<Order> orderOptional = this.orderRepository.findById(deliveryDto.getOrderId());
    if (orderOptional.isPresent()) {
      final Order order = orderOptional.get();
      this.orderRepository.save(order);
      return OrderMapper.INSTANCE.mapToOrderDto(order);
    }
    throw new OrderNotFoundException(ApplicationConstants.ORDER_NOT_FOUND_EXCEPTION);
  }

  @Override
  public void deleteOrder(final Long id) {
    final Optional<Order> orderOptional = this.orderRepository.findById(id);
    if (orderOptional.isPresent()) {
      final Order order = orderOptional.get();
      order.setDeleted(true);
      this.orderRepository.save(order);
      this.sendMessageToKafka(order);
      return;
    }
    throw new OrderNotFoundException(ApplicationConstants.ORDER_NOT_FOUND_EXCEPTION);
  }

  private void sendMessageToKafka(final Order order) {
    final OrderDeliveryDto orderDeliveryDto = new OrderDeliveryDto();
    orderDeliveryDto.setOrderId(order.getId());
    orderDeliveryDto.setTenantId(order.getTenantId());
    final KafkaMessage kafkaMessage = new KafkaMessage();
    kafkaMessage.setEventType(KafkaEventType.order_cancel);

    try {
      final String jsonData = new ObjectMapper().writeValueAsString(orderDeliveryDto);
      kafkaMessage.setData(jsonData);
      this.messageProducer.sendMessage(kafkaMessage);
      log.debug("Successfully sending message to Kafka : jsonData {{}}", jsonData);
    } catch (final JsonProcessingException e) {
      log.error(
          "Exception occurred while sending message to Kafka : deleteOrder {{}} with exception as {{}}",
          orderDeliveryDto, e);
    }
  }
}
