package com.dangtuan.order.kafka.consumer;

import com.dangtuan.kafka.KafkaMessage;
import com.dangtuan.order.kafka.event.DeliveryEventHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.concurrent.CountDownLatch;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class MessageListener {

  private CountDownLatch countDownLatch = new CountDownLatch(1);

  @Autowired
  private DeliveryEventHandler deliveryEventHandler;

  @KafkaListener(topics = "${kafka.order.topic.name}", containerFactory = "kafkaListenerContainerFactory")
  public void orderListener(KafkaMessage kafkaMessage) throws JsonProcessingException {
    log.info("Received order message: " + kafkaMessage);
    try {
      deliveryEventHandler.handleEvent(kafkaMessage);
    } catch (JsonProcessingException e) {
      log.error("Fail to process delivery message {}, exception {}", kafkaMessage,
          e.getStackTrace());
    }
    this.countDownLatch.countDown();
  }

  public CountDownLatch getCountDownLatch() {
    return countDownLatch;
  }
}
