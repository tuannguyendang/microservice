package com.dangtuan.order.config.kafka;

import com.dangtuan.order.dto.OrderDto;
import java.util.concurrent.CountDownLatch;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class MessageListener {

  private CountDownLatch greetingLatch = new CountDownLatch(1);

  @KafkaListener(topics = "${kafka.order.topic.name}", containerFactory = "greetingKafkaListenerContainerFactory")
  public void orderListener(OrderDto orderDto) {
    System.out.println("Received greeting message: " + orderDto);
    this.greetingLatch.countDown();
  }

  public CountDownLatch getGreetingLatch() {
    return greetingLatch;
  }
}
