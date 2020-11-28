package com.dangtuan.order.service.impl;

import com.dangtuan.dto.order.OrderDto;
import com.dangtuan.order.constants.ApplicationConstants;
import com.dangtuan.order.dto.UserSession;
import com.dangtuan.order.entity.Order;
import com.dangtuan.order.exception.OrderNotFoundException;
import com.dangtuan.order.mapper.OrderMapper;
import com.dangtuan.order.repository.OrderRepository;
import com.dangtuan.order.service.OrderService;
import java.util.Optional;
import javax.inject.Provider;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  Provider<UserSession> userSessionProvider;

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
  public void deleteOrder(final Long id) {
    final Optional<Order> orderOptional = this.orderRepository.findById(id);
    if (orderOptional.isPresent()) {
      final Order order = orderOptional.get();
      order.setDeleted(true);
      this.orderRepository.save(order);
      return;
    }
    throw new OrderNotFoundException(ApplicationConstants.ORDER_NOT_FOUND_EXCEPTION);
  }

}
