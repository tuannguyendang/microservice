package com.dangtuan.resource.service.service.impl;

import com.dangtuan.resource.service.dto.UserSession;
import java.util.Optional;
import javax.inject.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String> {

  @Autowired
  private Provider<UserSession> userSessionProvider;

  @Override
  public Optional<String> getCurrentAuditor() {
    final UserSession userSession = userSessionProvider.get();
    return Optional.of(userSession.getUserName());
  }
}
