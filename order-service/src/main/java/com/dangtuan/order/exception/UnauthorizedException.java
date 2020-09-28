package com.dangtuan.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown in case an unauthorized request is made.
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends ServiceException {

  /**
   * The Constant serialVersionUID.
   */
  private static final long serialVersionUID = -418514577885795898L;

  /**
   * The Constant HTTP_STATUS.
   */
  private static final HttpStatus HTTP_STATUS = HttpStatus.UNAUTHORIZED;

  /**
   * Instantiates a new bad request exception.
   *
   * @param errorMessage the error message
   */
  public UnauthorizedException(final String errorMessage) {
    super(errorMessage, HTTP_STATUS);
  }

  /**
   * Instantiates a new bad request exception.
   *
   * @param throwable the throwable
   */
  public UnauthorizedException(final Throwable throwable) {
    super(throwable, HTTP_STATUS);
  }
}
