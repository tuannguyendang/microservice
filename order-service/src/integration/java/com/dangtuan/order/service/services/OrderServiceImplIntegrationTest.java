package com.dangtuan.order.service.services;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import com.dangtuan.dto.order.OrderDto;
import com.dangtuan.order.service.BaseIntegrationTest;
import com.dangtuan.order.service.OrderService;
import java.util.HashMap;
import org.mockserver.model.HttpStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.Test;

public class OrderServiceImplIntegrationTest extends BaseIntegrationTest {

  @Autowired
  OrderService orderService;

  @Value("${api.payment}")
  private String PAYMENT_URL;

  @Test(dataProvider = PREPARE_ORDER_DTO)
  public void createOrderWhenInputValidWillSuccessTest(final OrderDto orderDto) {
    OrderDto orderDtoResult = orderService.createOrder(orderDto);
    assertNotNull(orderDtoResult);
    assertEquals(orderDtoResult.getAmount(), orderDto.getAmount());

    //sample call to mock server
    final HttpEntity<Object> entity = new HttpEntity<>(this.getHeaders());
    final ResponseEntity<HashMap> response = this.testRestTemplate
        .exchange(PAYMENT_URL, HttpMethod.POST, entity, HashMap.class);
    assertEquals(response.getStatusCodeValue(), HttpStatusCode.OK_200.code());
  }
}
