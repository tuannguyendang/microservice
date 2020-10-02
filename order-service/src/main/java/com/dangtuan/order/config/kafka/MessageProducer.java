package com.dangtuan.order.config.kafka;

import com.dangtuan.order.dto.OrderDto;
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
  private KafkaTemplate<String, OrderDto> orderKafkaTemplate;

  @Value(value = "${kafka.order.topic.name}")
  private String orderTopicName;

  public void sendMessage(OrderDto orderDto) {

    ListenableFuture<SendResult<String, OrderDto>> future = orderKafkaTemplate.send(orderTopicName, orderDto);

    future.addCallback(new ListenableFutureCallback<SendResult<String, OrderDto>>() {

      @Override
      public void onSuccess(SendResult<String, OrderDto> result) {
        System.out
            .println("Sent message=[" + orderDto + "] with offset=[" + result.getRecordMetadata()
                .offset() + "]");
      }

      @Override
      public void onFailure(Throwable ex) {
        System.out.println("Unable to send message=[" + orderDto + "] due to : " + ex.getMessage());
      }
    });
  }

  public void sendOrderMessage(OrderDto orderDto) {
    orderKafkaTemplate.send(orderTopicName, orderDto);
  }
}
