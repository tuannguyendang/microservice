package com.dangtuan.order.util.constants;

public class ApiConstants {

  public static final String CONFIGURATION_PROPERTIES = "app";
  public static final String API_VERSION = "v1";
  public static final String SEPARATOR = "/";

  public static final String ENDPOINTS_FOR_SWAGGER =
      SEPARATOR + "order-service" + SEPARATOR + API_VERSION + ".*";
  public static final String SWAGGER_REDIRECT = "redirect:/swagger-ui/index.html";
  public static final String GET_ORDER = "Get Order";
  public static final String GET_ORDER_NOTE = "Get Order By Id";
  public static final String GET_ORDER_SUCCESS = "Get Order Success";

  public static final String ORDER_API = API_VERSION + SEPARATOR + "order";
}
