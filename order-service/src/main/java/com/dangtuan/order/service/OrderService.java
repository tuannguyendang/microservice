package com.dangtuan.order.service;

import com.dangtuan.dto.delivery.DeliveryDto;
import com.dangtuan.dto.order.OrderDto;
import com.dangtuan.order.dto.filter.FilterCriteria;
import com.dangtuan.order.dto.response.OrderResponse;
import com.dangtuan.order.exception.OrderNotFoundException;
import org.springframework.data.domain.Page;

public interface OrderService {

  OrderDto getOrder(final Long id) throws OrderNotFoundException;

  OrderDto createOrder(final OrderDto orderDto);

  OrderDto updateOrder(final OrderDto orderDto, final Long id);

  OrderDto updateOrderDelivery(final DeliveryDto deliveryDto);

  void deleteOrder(final Long id);

  Page<OrderResponse> search(final FilterCriteria filterCriteria);

}
