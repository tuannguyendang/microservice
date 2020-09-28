package com.dangtuan.common.properties;

import com.dangtuan.common.util.constants.ApiConstants;
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
