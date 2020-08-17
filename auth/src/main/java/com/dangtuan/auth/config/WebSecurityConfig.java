package com.dangtuan.auth.config;

import com.dangtuan.auth.properties.AuthProperties;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private static final int strength = 4;
  private static final String BCRYPT = "bcrypt";

  @Autowired
  private AuthProperties authProperties;

  @Autowired
  private DataSource dataSource;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication()
        .dataSource(dataSource);
//    inMemorySetting(auth);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers(authProperties.getAllowedEndpoints()).permitAll()
        .and().authorizeRequests().anyRequest().denyAll()
        .and().csrf().disable();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder(strength);
    PasswordEncoder defaultEncoder = new BCryptPasswordEncoder();
    Map<String, PasswordEncoder> encoders = new HashMap<>();
    encoders.put(BCRYPT, new BCryptPasswordEncoder());

    DelegatingPasswordEncoder passworEncoder = new DelegatingPasswordEncoder(
        BCRYPT, encoders);
    passworEncoder.setDefaultPasswordEncoderForMatches(defaultEncoder);

    return passworEncoder;
  }

  /**
   * Memory authentication
   *
   * @param auth
   * @throws Exception
   */
  private void inMemorySetting(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser("user").roles("SG_USER").password("123456")
        .and().withUser("admin").roles("SG_ADMIN").password("123456");
  }
}
