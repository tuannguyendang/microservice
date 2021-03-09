package com.dangtuan.order.service;

import com.dangtuan.dto.order.OrderDto;
import com.dangtuan.order.dto.UserSession;
import com.dangtuan.order.entity.Order;
import java.math.BigDecimal;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.DataProvider;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@ActiveProfiles(BaseIntegrationTest.INTEGRATION_TEST_PROFILE)
@Rollback(true)
public class BaseIntegrationTest extends AbstractTransactionalTestNGSpringContextTests {

  protected static final String PREPARE_ORDER_DTO = "prepareOrderDto";
  protected static final String INTEGRATION_TEST_PROFILE = "integration";

  @Autowired
  @Qualifier(ConfigIntegrationTest.USER_SESSION)
  UserSession testUserSession;

  @DataProvider
  public Object[][] prepareOrderDto() {
    return new Object[][]{
        {createOrderDto()}
    };
  }

  public UserSession loggedUser() {
    final UserSession userSession = new UserSession();
    userSession.setTenantId("tenant_1");
    userSession.setUserName("tuan193@gmail.com");
    return userSession;
  }

  public OrderDto createOrderDto() {
    OrderDto orderDto = new OrderDto();
    orderDto.setAmount(100l);
    orderDto.setPrice(BigDecimal.valueOf(100.11));
    return orderDto;
  }

  public Order createOrder() {
    Order order = new Order();
    order.setId(1L);
    order.setAmount(100L);
    order.setPrice(BigDecimal.valueOf(100.11));
    return order;
  }
}
