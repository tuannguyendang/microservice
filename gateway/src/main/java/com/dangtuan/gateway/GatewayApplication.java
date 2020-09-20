package com.dangtuan.gateway;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import java.util.List;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Setter
@EnableZuulProxy
@EnableDiscoveryClient
@ConfigurationProperties("app.cors")
@EnableEncryptableProperties
@SpringBootApplication
public class GatewayApplication {

  private List<String> origins;

  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class, args);
  }

  public CorsFilter corsFilter() {
    CorsConfiguration corsConfig = new CorsConfiguration();
    corsConfig.setAllowCredentials(Boolean.TRUE);
    corsConfig.setAllowedOrigins(origins);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfig);
    return new CorsFilter(source);
  }
}
