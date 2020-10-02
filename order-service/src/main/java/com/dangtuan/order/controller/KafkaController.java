package com.dangtuan.order.controller;

import com.dangtuan.order.config.kafka.MessageListener;
import com.dangtuan.order.config.kafka.MessageProducer;
import com.dangtuan.order.dto.OrderDto;
import com.dangtuan.order.util.constants.ApiConstants;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = ApiConstants.KAFKA_API, produces = MediaType.APPLICATION_JSON_VALUE)
public class KafkaController {

  @Autowired
  private MessageProducer messageProducer;

  @Autowired
  private MessageListener messageListener;

  @GetMapping
  public ResponseEntity<Void> kafka() throws InterruptedException {
    final OrderDto orderDto = new OrderDto();
    orderDto.setAmount(100l);
    messageProducer.sendOrderMessage(orderDto);
    messageListener.getGreetingLatch().await(10, TimeUnit.SECONDS);

    messageProducer.sendMessage(orderDto);
    messageListener.getGreetingLatch().await(10, TimeUnit.SECONDS);

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
