package com.dangtuan.order.kafka.producer;

import com.dangtuan.kafka.KafkaMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Log4j2
@Service
public class MessageProducer {

  @Autowired
  private KafkaTemplate<String, KafkaMessage> orderKafkaTemplate;

  @Value(value = "${kafka.delivery.topic.name}")
  private String orderTopicName;

  public void sendMessageCallback(KafkaMessage kafkaMessage) {

    ListenableFuture<SendResult<String, KafkaMessage>> future = orderKafkaTemplate
        .send(orderTopicName, kafkaMessage);

    future.addCallback(new ListenableFutureCallback<SendResult<String, KafkaMessage>>() {

      @Override
      public void onSuccess(SendResult<String, KafkaMessage> result) {
        log.info("Sent message=[" + kafkaMessage + "] with offset=[" + result.getRecordMetadata()
            .offset() + "]");
      }

      @Override
      public void onFailure(Throwable ex) {
        log.info("Unable to send message=[" + kafkaMessage + "] due to : " + ex.getMessage());
      }
    });
  }

  public void sendMessage(KafkaMessage kafkaMessage) {
    orderKafkaTemplate.send(orderTopicName, kafkaMessage);
  }
}
