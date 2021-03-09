package com.dangtuan.order.service;

import com.dangtuan.dto.order.OrderDto;
import com.dangtuan.order.dto.UserSession;
import com.dangtuan.order.entity.Order;
import java.math.BigDecimal;
import org.testng.annotations.DataProvider;

public interface BaseTest {

  String CREATE_ORDER = "getOrder";
  String PREPARE_ORDER_DTO = "prepareOrderDto";

  @DataProvider
  default Object[][] getOrder() {
    return new Object[][]{
        {createOrder()}
    };
  }

  @DataProvider
  default Object[][] prepareOrderDto() {
    return new Object[][]{
        {createOrderDto()}
    };
  }

  default UserSession loggedUser() {
    final UserSession userSession = new UserSession();
    userSession.setTenantId("tenant_1");
    userSession.setUserName("tuan193@gmail.com");
    return userSession;
  }

  default Order createOrder() {
    Order order = new Order();
    order.setId(1L);
    order.setAmount(100L);
    order.setPrice(BigDecimal.valueOf(100.11));
    return order;
  }

  default OrderDto createOrderDto() {
    OrderDto orderDto = new OrderDto();
    orderDto.setAmount(100l);
    orderDto.setPrice(BigDecimal.valueOf(100.11));
    return orderDto;
  }
}
