package com.dangtuan.auth.dto;

import java.io.Serializable;
import org.springframework.security.core.GrantedAuthority;

/**
 * Class handle customize authority
 */
public class CustomGrantedAuthority implements GrantedAuthority, Serializable {

  private String name;

  public CustomGrantedAuthority(String name) {
    this.name = name;
  }

  @Override
  public String getAuthority() {
    return name;
  }

}
