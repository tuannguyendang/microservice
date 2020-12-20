package com.dangtuan.delivery.controller;

import com.dangtuan.delivery.service.DeliveryService;
import com.dangtuan.delivery.util.constants.ApiConstants;
import com.dangtuan.dto.delivery.DeliveryDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = ApiConstants.DELIVERY_API, produces = MediaType.APPLICATION_JSON_VALUE)
@Log4j2
public class DeliveryController {

  @Autowired
  private DeliveryService deliveryService;

  @PutMapping(value = ApiConstants.DELIVERY_DELIVERED)
  public ResponseEntity<Void> updateDelivered(@RequestBody final DeliveryDto deliveryDto)
      throws InterruptedException {
    deliveryService.notificationToOrder(deliveryDto);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping(value = ApiConstants.SENTRY_API)
  void triggerEventSentry() throws Exception {
    deliveryService.sentryTrigger();
    log.error("Event triggered by sentry integration");
    throw new IllegalArgumentException("Event triggered sentry by Spring integration");
  }
}
