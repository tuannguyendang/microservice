package com.dangtuan.order.service.services;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import com.dangtuan.dto.order.OrderDto;
import com.dangtuan.order.dto.UserSession;
import com.dangtuan.order.entity.Order;
import com.dangtuan.order.kafka.producer.MessageProducer;
import com.dangtuan.order.mapper.OrderMapper;
import com.dangtuan.order.repository.OrderRepository;
import com.dangtuan.order.service.BaseTest;
import com.dangtuan.order.service.impl.OrderServiceImpl;
import javax.inject.Provider;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrderServiceImplTest implements BaseTest {

  @InjectMocks
  private OrderServiceImpl orderService;

  @Mock
  private OrderRepository orderRepository;

  @Mock
  private Provider<UserSession> userSessionProvider;

  @Mock
  private MessageProducer messageProducer;

  @BeforeMethod(alwaysRun = true)
  public void initMock() {
    MockitoAnnotations.openMocks(this);
  }

  @Test(dataProvider = CREATE_ORDER)
  public void getOrderWhenIdValidWillSuccessTest(final Order order) {
    when(orderRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(order));
    OrderDto orderDto = OrderMapper.INSTANCE.mapToOrderDto(order);
    OrderDto orderDtoResult = orderService.getOrder(1L);
    assertNotNull(orderDtoResult);
    assertEquals(orderDto.getAmount(), orderDtoResult.getAmount());
    verify(orderRepository, times(1)).findById(1L);
  }

  @Test(dataProvider = PREPARE_ORDER_DTO)
  public void createOrderWhenInputValidWillSuccessTest(final OrderDto orderDto) {
    assertEquals(true, true);
    final UserSession userSession = loggedUser();
    Order order = OrderMapper.INSTANCE.mapToOrder(orderDto);

    when(userSessionProvider.get()).thenReturn(userSession);
    when(orderRepository.save(order)).thenReturn(order);

    OrderDto orderDtoTest = orderService.createOrder(orderDto);
    assertNotNull(orderDtoTest);
    assertEquals(orderDtoTest.getAmount(), orderDto.getAmount());
    verify(orderRepository, times(1)).save(order);
  }

  @Test(dataProvider = CREATE_ORDER)
  public void deleteOrderWhenIdValidSuccessTest(final Order order) throws Exception {
    when(orderRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(order));
    when(orderRepository.save(order)).thenReturn(order);

    orderService.deleteOrder(1L);
  }

  @Test(enabled = false, dataProvider = CREATE_ORDER)
  public void testWhenSomethingWillReturnSomething(final Order order) {
    assertEquals(true, true);
  }
}
