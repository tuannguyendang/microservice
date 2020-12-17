package com.dangtuan.kafka;

public enum KafkaEventType {
  order_create,
  order_cancel,
  order_delete,
  order_update,

  delivery_create,
  delivery_cancel,
  delivery_delete,
  delivery_update,
  delivery_delivered,

  default_none
}
