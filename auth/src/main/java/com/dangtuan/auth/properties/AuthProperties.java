package com.dangtuan.auth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("app")
@Component
@Data
public class AuthProperties {

  private String[] allowedEndpoints;
  private String tokenKeyAlias;
  private String tokenKeyStore;
  private String tokenKeyStorePass;
  private String tokenKeyPairPass;
}
