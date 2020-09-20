package com.dangtuan.resource.service.config;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.dangtuan.resource.service.util.constants.ApiConstants;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger API document configuration.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  private static final String BASE_PACKAGE = "com.dangtuan";
  private static final String CONTACT_NAME = "tuannguyen";
  private static final String CONTACT_SITE = "https://www.linkedin.com/in/tuan-nguyen-dang-b97b8366/";
  private static final String CONTACT_EMAIL = "tuan193@gmail.com";
  private static final String API_TITLE = "Order Service";
  private static final String API_DESC = "Order service APIs.";
  private static final String API_LICENSE = "License";

  public static final String ACCESS_TOKEN_HEADER = "header";
  public static final String ACCESS_TOKEN_HEADER_NAME = "Authorization";
  public static final String AUTHORIZATION_SCOPE = "global";

  private final Predicate<String> apiPathSelector = PathSelectors
      .regex(ApiConstants.ENDPOINTS_FOR_SWAGGER);

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
        .paths(apiPathSelector)
        .build().apiInfo(apiInformation())
        .securityContexts(Arrays.asList(securityContext()))
        .securitySchemes(Arrays.asList(apiKey()));
  }

  private ApiInfo apiInformation() {
    Contact contact = new Contact(CONTACT_NAME, CONTACT_SITE, CONTACT_EMAIL);

    return new ApiInfoBuilder().title(API_TITLE)
        .description(API_DESC)
        .version(ApiConstants.API_VERSION)
        .contact(contact)
        .license(API_LICENSE).build();
  }

  @Bean
  public SecurityConfiguration security() {
    return SecurityConfigurationBuilder.builder()
        .clientId(null)
        .clientSecret(null)
        .scopeSeparator(null)
        .useBasicAuthenticationWithAccessCodeGrant(true)
        .build();
  }

  private ApiKey apiKey() {
    return new ApiKey(AUTHORIZATION, ACCESS_TOKEN_HEADER_NAME,
        ACCESS_TOKEN_HEADER);
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder()
        .securityReferences(defaultAuth())
        .forPaths(PathSelectors.regex("/*.*"))
        .build();
  }

  private List<SecurityReference> defaultAuth() {
    final AuthorizationScope authorizationScope
        = new AuthorizationScope(AUTHORIZATION_SCOPE,
        AUTHORIZATION_SCOPE);
    final AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Arrays.asList(
        new SecurityReference(AUTHORIZATION, authorizationScopes));
  }

}
