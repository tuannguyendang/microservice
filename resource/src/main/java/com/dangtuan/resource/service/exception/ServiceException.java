package com.dangtuan.resource.service.exception;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

/**
 * The Service Exception.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends RuntimeException {

  /**
   * The Constant serialVersionUID.
   */
  private static final long serialVersionUID = -1552617640040732073L;
  /** The Constant serialVersionUID. */

  /**
   * The HTTP status.
   */
  private final HttpStatus httpStatus;

  /**
   * Additional Parameters
   */
  private final Map<String, Object> params = new HashMap<>();

  /**
   * Instantiates a new service exception.
   *
   * @param errorMessage the error message
   * @param httpStatus   the HTTP status
   */
  public ServiceException(final String errorMessage, final HttpStatus httpStatus) {
    super(errorMessage);
    this.httpStatus = httpStatus;
  }

  /**
   * Instantiates a new service exception.
   *
   * @param throwable the throwable
   */
  public ServiceException(final Throwable throwable, final HttpStatus httpStatus) {
    super(throwable);
    this.httpStatus = httpStatus;
  }

  /**
   * Instantiates a new service exception.
   *
   * @param errorMessage the errorMessage
   * @param throwable    the throwable
   * @param httpStatus   the httpStatus
   */
  public ServiceException(final String errorMessage, final Throwable throwable,
      final HttpStatus httpStatus) {
    super(errorMessage, throwable);
    this.httpStatus = httpStatus;
  }

  /**
   * Utility to check if HTTP status is for 404.
   *
   * @return true if 404
   */
  public boolean isNotFound() {
    return HttpStatus.NOT_FOUND == httpStatus;
  }

  public HttpStatus getStatus() {
    return this.httpStatus;
  }

  public Map<String, Object> getParams() {
    return Collections.unmodifiableMap(this.params);
  }

  /**
   * Adding additional parameters to exception object
   *
   * @return ServiceException updated exception object
   */
  public ServiceException addParams(final String key, final Object value) {
    this.params.put(key, value);
    return this;
  }

  @Override
  public String getMessage() {
    final StringBuilder sb = new StringBuilder(super.getMessage());
    this.params.forEach((k, v) -> {
      sb.append(k).append(StringUtils.SPACE).append(":").append(StringUtils.SPACE).append(v);
    });
    return sb.toString();
  }
}
