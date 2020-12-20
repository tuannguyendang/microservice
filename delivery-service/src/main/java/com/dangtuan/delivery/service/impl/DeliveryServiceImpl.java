package com.dangtuan.delivery.service.impl;

import com.dangtuan.delivery.kafka.producer.MessageProducer;
import com.dangtuan.delivery.service.DeliveryService;
import com.dangtuan.dto.delivery.DeliveryDto;
import com.dangtuan.dto.order.OrderDeliveryDto;
import com.dangtuan.kafka.KafkaEventType;
import com.dangtuan.kafka.KafkaMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class DeliveryServiceImpl implements DeliveryService {

  @Autowired
  private MessageProducer messageProducer;

  @Override
  public void notificationToOrder(final DeliveryDto deliveryDto) {
    final KafkaMessage kafkaMessage = new KafkaMessage();
    kafkaMessage.setEventType(KafkaEventType.delivery_delivered);

    try {
      final String jsonData = new ObjectMapper().writeValueAsString(deliveryDto);
      kafkaMessage.setData(jsonData);
      this.messageProducer.sendMessage(kafkaMessage);
      log.debug("Successfully sending message to Kafka : jsonData {{}}", jsonData);
    } catch (final JsonProcessingException e) {
      log.error(
          "Exception occurred while sending message to Kafka : notificationToOrder {{}} with exception as {{}}",
          deliveryDto, e);
    }
  }

  @Override
  public void cancelDelivery(final OrderDeliveryDto orderDeliveryDto) {
    log.info("Cancel delivery for order id {}, tenant id {}, delivery id {}",
        orderDeliveryDto.getOrderId(), orderDeliveryDto.getTenantId(),
        orderDeliveryDto.getDeliveryId());
  }

  @Override
  public void sentryTrigger() throws Exception {
    log.error("Trigger sentry exception in service layer!");
    throw new Exception("Trigger sentry exception in service layer!");
  }
}
