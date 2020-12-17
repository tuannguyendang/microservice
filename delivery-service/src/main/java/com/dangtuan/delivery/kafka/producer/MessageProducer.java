package com.dangtuan.delivery.kafka.producer;

import com.dangtuan.kafka.KafkaMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class MessageProducer {

  @Autowired
  private KafkaTemplate<String, KafkaMessage> orderKafkaTemplate;

  @Value(value = "${kafka.order.topic.name}")
  private String orderTopicName;

  public void sendMessage(KafkaMessage kafkaMessage) {
    orderKafkaTemplate.send(orderTopicName, kafkaMessage);
  }
}
