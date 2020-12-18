package com.dangtuan.delivery.kafka.event;

import com.dangtuan.delivery.service.DeliveryService;
import com.dangtuan.dto.order.OrderDeliveryDto;
import com.dangtuan.kafka.KafkaMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class OrderEventHandler implements KafkaEventHandler {

  @Autowired
  private DeliveryService deliveryService;

  @Override
  public void handleEvent(KafkaMessage kafkaMessage) throws JsonProcessingException {
    switch (kafkaMessage.getEventType()) {
      case order_cancel:
        final OrderDeliveryDto orderDeliveryDto =
            new ObjectMapper().readValue(kafkaMessage.getData(), OrderDeliveryDto.class);
        deliveryService.cancelDelivery(orderDeliveryDto);
        break;
      default:
        log.info("event type not handle yet");
        break;
    }
  }
}
