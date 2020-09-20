package com.dangtuan.resource.service.service.impl;

import com.dangtuan.resource.service.dto.MatcherMappingDto;
import com.dangtuan.resource.service.entity.Matcher;
import com.dangtuan.resource.service.repository.MatcherRepository;
import com.dangtuan.resource.service.service.MatcherService;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MatcherServiceImpl implements MatcherService {

  @Autowired
  MatcherRepository matcherRepository;

  @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
  public List<MatcherMappingDto> getMatchers() {
    List<Matcher> matchers = matcherRepository
        .findAll();
    if (!matchers.isEmpty()) {
      return matchers.stream().map(
          matcher -> new MatcherMappingDto(matcher.getEndpoint(), matcher.getAuthority().getName(),
              matcher.getMethodType()))
          .collect(
              Collectors.toList());
    }
    return Collections.emptyList();
  }
}
