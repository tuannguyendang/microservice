package com.dangtuan.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends ServiceException {

  private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

  public OrderNotFoundException(String errorMessage) {
    super(errorMessage, HTTP_STATUS);
  }

}
