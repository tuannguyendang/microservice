package com.dangtuan.order.service.services;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import com.dangtuan.dto.order.OrderDto;
import com.dangtuan.order.service.BaseIntegrationTest;
import com.dangtuan.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class OrderServiceImplIntegrationTest extends BaseIntegrationTest {

  @Autowired
  OrderService orderService;

  @Test(dataProvider = PREPARE_ORDER_DTO)
  public void createOrderWhenInputValidWillSuccessTest(final OrderDto orderDto) {
    OrderDto orderDtoResult = orderService.createOrder(orderDto);
    assertNotNull(orderDtoResult);
    assertEquals(orderDtoResult.getAmount(), orderDto.getAmount());
  }
}
