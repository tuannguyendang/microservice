package com.dangtuan.order.service.services;


import com.dangtuan.kafka.KafkaMessage;
import com.dangtuan.order.entity.Order;
import com.dangtuan.order.kafka.producer.MessageProducer;
import com.dangtuan.order.service.BaseTest;
import com.dangtuan.order.service.impl.OrderServiceImpl;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.reflect.internal.WhiteboxImpl;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


@PrepareForTest(fullyQualifiedNames = "com.dangtuan.order.service.impl.OrderServiceImpl")
public class OrderServiceImplTestPrivate implements BaseTest {

  @InjectMocks
  OrderServiceImpl orderService;
  @Mock
  MessageProducer messageProducer;

  @BeforeMethod(alwaysRun = true)
  public void initMock() {
    MockitoAnnotations.openMocks(this);
  }

  @Test(dataProvider = CREATE_ORDER)
  public void testSendMessageKafkaWhenInputValidWillSuccessTest(final Order order)
      throws Exception {
    PowerMockito.doNothing()
        .when(messageProducer, "sendMessage", ArgumentMatchers.any(KafkaMessage.class));
    WhiteboxImpl.invokeMethod(orderService, "sendMessageToKafka", order);
  }

  /**
   * @mock can not inject object to @spy, spy Test private method
   * Use for testing Utility private method without inject bean
   */
  @Test(enabled = true, dataProvider = CREATE_ORDER, expectedExceptions = NullPointerException.class)
  public void testPrivateTest(final Order order)
      throws Exception {
    OrderServiceImpl mock = PowerMockito.spy(new OrderServiceImpl());
    PowerMockito.when(mock, "sendMessageToKafka", order);
  }
}
