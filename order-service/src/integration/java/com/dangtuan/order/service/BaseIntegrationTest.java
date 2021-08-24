package com.dangtuan.order.service;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.dangtuan.dto.order.OrderDto;
import com.dangtuan.order.dto.UserSession;
import com.dangtuan.order.entity.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.transaction.Transactional;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.HttpStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.web.bind.annotation.RequestMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@ActiveProfiles(BaseIntegrationTest.INTEGRATION_TEST_PROFILE)
@Rollback(true)
public class BaseIntegrationTest extends AbstractTransactionalTestNGSpringContextTests {

  protected static final String PREPARE_ORDER_DTO = "prepareOrderDto";
  protected static final String INTEGRATION_TEST_PROFILE = "integration";
  private static volatile AtomicBoolean initialized = new AtomicBoolean(Boolean.FALSE);
  public static final String CONTENT_TYPE = "content-type";
  private static ClientAndServer mockServer;

  @Value(value = "${server.mock-server-port}")
  private int mockServerPort;

  @Autowired
  protected TestRestTemplate testRestTemplate;

  @Autowired
  @Qualifier(ConfigIntegrationTest.USER_SESSION)
  UserSession testUserSession;

  @BeforeClass
  public void startServer() throws JsonProcessingException {
    if (initialized.compareAndSet(Boolean.FALSE, Boolean.TRUE)) {
      mockServer = startClientAndServer(mockServerPort);

      mockServer.when(new HttpRequest().withMethod(RequestMethod.POST.name())
          .withPath("/v1/payment"))
          .respond(new HttpResponse().withStatusCode(HttpStatusCode.OK_200.code())
              .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
              .withBody("{\"id\":\"lalamove-1111\"}"));
    }
  }

  @AfterSuite
  public void stopServer() {
    mockServer.stop();
  }

  @DataProvider
  public Object[][] prepareOrderDto() {
    return new Object[][]{
        {createOrderDto()}
    };
  }

  protected HttpHeaders getHeaders() {
    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return headers;
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
