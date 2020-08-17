package com.dangtuan.auth.properties;

import com.dangtuan.auth.util.constants.Constants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(Constants.CONFIGURATION_PROPERTIES)
@Component
@Data
public class AuthProperties {

  private String[] allowedEndpoints;
  private String tokenKeyAlias;
  private String tokenKeyStore;
  private String tokenKeyStorePass;
  private String tokenKeyPairPass;
}
