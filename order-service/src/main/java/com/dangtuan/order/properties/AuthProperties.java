package com.dangtuan.order.properties;

import com.dangtuan.order.util.constants.ApiConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(ApiConstants.CONFIGURATION_PROPERTIES)
@Component
@Data
public class AuthProperties {

  private String[] allowedEndpoints;
  private String publicKey;
}
