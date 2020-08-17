package com.dangtuan.auth.config;

import com.dangtuan.auth.properties.AuthProperties;
import java.security.KeyPair;
import java.util.Arrays;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
public class Oauth2AuthorizationServer extends AuthorizationServerConfigurerAdapter {

  private static final String PERMIT_ALL = "permitAll()";
  private static final String IS_AUTHENTICATED = "isAuthenticated()";
  @Autowired
  private DataSource dataSource;

  @Autowired
  private AuthProperties authProperties;

  @Autowired
  TokenEnhancer customTokenEnhancer;

  @Autowired
  @Qualifier("authenticationManagerBean")
  AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailsService userDetailsService;

  /**
   * allowFormAuthenticationForClients instead basic authentication
   *
   * @param security
   * @throws Exception
   */
  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security.tokenKeyAccess(PERMIT_ALL).checkTokenAccess(IS_AUTHENTICATED)
        .allowFormAuthenticationForClients();
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//    JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
//    clients.withClientDetails(jdbcClientDetailsService);
    clients.jdbc(dataSource);
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
    tokenEnhancerChain
        .setTokenEnhancers(Arrays.asList(customTokenEnhancer, accessTokenConverter()));
    endpoints.tokenStore(tokenStore())
        .tokenEnhancer(tokenEnhancerChain)
        .authenticationManager(authenticationManager)
        .userDetailsService(userDetailsService);
  }

  @Bean
  @Primary
  public DefaultTokenServices tokenServices() {
    DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
    defaultTokenServices.setTokenStore(tokenStore());
    defaultTokenServices.setSupportRefreshToken(true);
    defaultTokenServices.setTokenEnhancer(customTokenEnhancer);
    return defaultTokenServices;
  }

  /**
   * Definition where token generated will store (in memory, in database)
   *
   * @return
   */
  @Bean
  public TokenStore tokenStore() {
//    return new JwtTokenStore(accessTokenConverter());
    return new JdbcTokenStore(dataSource);
  }

  @Bean
  public JwtAccessTokenConverter accessTokenConverter() {
    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    converter.setKeyPair(keyPair(keyStoreKeyFactory()));
    return converter;
  }

  private KeyPair keyPair(KeyStoreKeyFactory keyStoreKeyFactory) {
    return keyStoreKeyFactory.getKeyPair(authProperties.getTokenKeyAlias(),
        authProperties.getTokenKeyPairPass().toCharArray());
  }

  private KeyStoreKeyFactory keyStoreKeyFactory() {
    return new KeyStoreKeyFactory(new ClassPathResource(authProperties.getTokenKeyStore()),
        authProperties.getTokenKeyStorePass().toCharArray());
  }
}
