package com.dangtuan.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoggingFilter extends ZuulFilter {

  @Override
  public String filterType() {
    return "pre";
  }

  @Override
  public int filterOrder() {
    return 0;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() throws ZuulException {
    HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
    log.info("Request -> {} with request URL: {}", request, request.getRequestURL());
    return null;
  }
}
