package com.dangtuan.order.kafka.event;

import com.dangtuan.kafka.KafkaMessage;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface KafkaEventHandler {
  void handleEvent(final KafkaMessage kafkaMessage) throws JsonProcessingException;
}
