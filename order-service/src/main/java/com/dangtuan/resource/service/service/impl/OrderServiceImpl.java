package com.dangtuan.resource.service.service.impl;

import com.dangtuan.resource.service.constants.ApplicationConstants;
import com.dangtuan.resource.service.dto.OrderDto;
import com.dangtuan.resource.service.entity.Order;
import com.dangtuan.resource.service.exception.OrderNotFoundException;
import com.dangtuan.resource.service.mapper.OrderMapper;
import com.dangtuan.resource.service.repository.OrderRepository;
import com.dangtuan.resource.service.service.OrderService;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

  @Autowired
  private OrderRepository orderRepository;

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
    final Order order = OrderMapper.INSTANCE.mapToOrder(orderDto);
    this.orderRepository.save(order);
    return OrderMapper.INSTANCE.mapToOrderDto(order);
  }

  @Override
  public OrderDto updateOrder(final OrderDto orderDto, final Long id) {
    final Optional<Order> orderOptional = this.orderRepository.findById(id);
    if (orderOptional.isPresent()) {
      final Order order = orderOptional.get();
      OrderMapper.INSTANCE.mapToUpdate(orderDto, order);
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
