package com.dangtuan.order.service;

import com.dangtuan.dto.delivery.DeliveryDto;
import com.dangtuan.dto.order.OrderDto;
import com.dangtuan.order.exception.OrderNotFoundException;

public interface OrderService {

  OrderDto getOrder(final Long id) throws OrderNotFoundException;

  OrderDto createOrder(final OrderDto orderDto);

  OrderDto updateOrder(final OrderDto orderDto, final Long id);

  OrderDto updateOrderDelivery(final DeliveryDto deliveryDto);

  void deleteOrder(final Long id);
}
