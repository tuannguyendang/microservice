package com.dangtuan.order.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class Utils {
  public static HttpHeaders getHeaders() {
    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return headers;
  }
}
