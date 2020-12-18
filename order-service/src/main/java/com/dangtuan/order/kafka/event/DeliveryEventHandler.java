package com.dangtuan.order.kafka.event;

import com.dangtuan.dto.delivery.DeliveryDto;
import com.dangtuan.kafka.KafkaMessage;
import com.dangtuan.order.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class DeliveryEventHandler implements KafkaEventHandler {

  @Autowired
  private OrderService orderService;

  @Override
  public void handleEvent(KafkaMessage kafkaMessage) throws JsonProcessingException {
    switch (kafkaMessage.getEventType()) {
      case delivery_delivered:
        final DeliveryDto deliveryDto =
            new ObjectMapper().readValue(kafkaMessage.getData(), DeliveryDto.class);
        orderService.updateOrderDelivery(deliveryDto);
        break;
      default:
        log.info("Event type not handle yet!");
        break;
    }
  }
}
