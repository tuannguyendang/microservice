package com.dangtuan.order.service;

import com.dangtuan.order.dto.OrderDto;
import com.dangtuan.order.exception.OrderNotFoundException;

public interface OrderService {

  OrderDto getOrder(final Long id) throws OrderNotFoundException;

  OrderDto createOrder(final OrderDto orderDto);

  OrderDto updateOrder(final OrderDto orderDto, final Long id);

  void deleteOrder(final Long id);
}
