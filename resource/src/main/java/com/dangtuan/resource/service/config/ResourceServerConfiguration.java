package com.dangtuan.resource.service.config;

import com.dangtuan.resource.service.dto.MatcherMappingDto;
import com.dangtuan.resource.service.properties.AuthProperties;
import com.dangtuan.resource.service.service.MatcherService;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

  @Autowired
  private MatcherService matcherService;

  @Autowired
  private CustomTokenExtractor tokenExtractor;

  @Autowired
  private AuthProperties authProperties;

  @Override
  public void configure(ResourceServerSecurityConfigurer config) {
    config.tokenServices(tokenServices()).tokenExtractor(tokenExtractor);
  }

  @Bean
  public TokenStore tokenStore() {
    return new JwtTokenStore(accessTokenConverter());
  }

  @Bean
  public JwtAccessTokenConverter accessTokenConverter() {
    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    Resource resource = new ClassPathResource(authProperties.getPublicKey());
    String publicKey;
    try {
      publicKey = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
    } catch (final IOException e) {
      throw new RuntimeException(e);
    }
    converter.setVerifierKey(publicKey);
    return converter;
  }

  @Bean
  @Primary
  public DefaultTokenServices tokenServices() {
    DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
    defaultTokenServices.setTokenStore(tokenStore());
    return defaultTokenServices;
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    List<MatcherMappingDto> matchers = matcherService.getMatchers();
    http.httpBasic().disable().anonymous().and().authorizeRequests()
        .antMatchers(authProperties.getAllowedEndpoints()).permitAll();
    for (MatcherMappingDto matcher : matchers) {
      http.authorizeRequests()
          .antMatchers(HttpMethod.valueOf(matcher.getMethodType()), matcher.getEndpoint())
          .access("hasAuthority('" + matcher.getAuthority() + "')");
    }
  }
}
