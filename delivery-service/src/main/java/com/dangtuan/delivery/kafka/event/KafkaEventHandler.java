package com.dangtuan.delivery.kafka.event;

import com.dangtuan.kafka.KafkaMessage;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface KafkaEventHandler {
  void handleEvent(final KafkaMessage kafkaMessage) throws JsonProcessingException;
}
