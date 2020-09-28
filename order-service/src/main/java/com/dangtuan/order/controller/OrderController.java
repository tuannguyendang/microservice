package com.dangtuan.order.controller;

import com.dangtuan.order.dto.OrderDto;
import com.dangtuan.order.service.MatcherService;
import com.dangtuan.order.service.OrderService;
import com.dangtuan.order.util.constants.ApiConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = ApiConstants.ORDER_API, produces = MediaType.APPLICATION_JSON_VALUE)
@Log4j2
@Api(value = ApiConstants.ORDER_API)
public class OrderController {

  @Autowired
  private OrderService orderService;

  @Autowired
  private MatcherService matcherService;

  @ApiOperation(value = ApiConstants.GET_ORDER,
      notes = ApiConstants.GET_ORDER_NOTE,
      response = OrderDto.class)
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = ApiConstants.GET_ORDER_SUCCESS)
      })
  @GetMapping("/{id}")
  public ResponseEntity<OrderDto> getOrder(@PathVariable final Long id) {
    final OrderDto orderDto = this.orderService.getOrder(id);
    return new ResponseEntity<>(orderDto, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<OrderDto> createOrder(@RequestBody final OrderDto orderDto) {
    return new ResponseEntity<>(this.orderService.createOrder(orderDto), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<OrderDto> updateOrder(@RequestBody final OrderDto orderDto,
      @PathVariable final Long id) {
    return new ResponseEntity<>(this.orderService.updateOrder(orderDto, id), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteOrder(@PathVariable final Long id) {
    this.orderService.deleteOrder(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
