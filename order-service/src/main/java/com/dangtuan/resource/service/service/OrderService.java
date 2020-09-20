package com.dangtuan.resource.service.service;

import com.dangtuan.resource.service.dto.OrderDto;
import com.dangtuan.resource.service.exception.OrderNotFoundException;

public interface OrderService {

  OrderDto getOrder(final Long id) throws OrderNotFoundException;

  OrderDto createOrder(final OrderDto orderDto);

  OrderDto updateOrder(final OrderDto orderDto, final Long id);

  void deleteOrder(final Long id);
}
