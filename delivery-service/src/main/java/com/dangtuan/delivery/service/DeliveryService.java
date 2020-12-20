package com.dangtuan.delivery.service;

import com.dangtuan.dto.delivery.DeliveryDto;
import com.dangtuan.dto.order.OrderDeliveryDto;

public interface DeliveryService {
  void notificationToOrder(final DeliveryDto deliveryDto);
  void cancelDelivery(final OrderDeliveryDto orderDeliveryDto);
  void sentryTrigger() throws Exception;
}
