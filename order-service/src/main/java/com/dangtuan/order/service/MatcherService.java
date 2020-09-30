package com.dangtuan.order.service;

import com.dangtuan.order.dto.MatcherMappingDto;
import java.util.List;

public interface MatcherService {

  List<MatcherMappingDto> getMatchers();
}
