package com.dangtuan.auth.dto;

import java.util.Collection;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
public class CustomUserDetails implements UserDetails {

  private String userName;

  private String password;

  private Set<GrantedAuthority> grantedAuthorities;

  private Boolean accountExpired;

  private Boolean accountLocked;

  private Boolean credentialsExpired;

  private Boolean enabled;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.grantedAuthorities;
  }

  @Override
  public String getUsername() {
    return this.userName;
  }

  @Override
  public boolean isAccountNonExpired() {
    log.debug("accountExpired {} ", this.accountExpired);
    return !this.accountExpired;
  }

  @Override
  public boolean isAccountNonLocked() {
    log.debug("accountLocked {} ", this.accountLocked);
    return !this.accountLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    log.debug("credentialsExpired {} ", this.credentialsExpired);
    return !this.credentialsExpired;
  }

  @Override
  public boolean isEnabled() {
    log.debug("enabled {} ", this.enabled);
    return this.enabled;
  }
}

