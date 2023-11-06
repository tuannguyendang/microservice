package com.dangtuan.order.dto.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * Filter criteria.
 */
@Data
@AllArgsConstructor
@Builder
public class FilterCriteria {

  private final String fullTextSearch;
  private final String query;
  private final int page;
  private final int limit;
  private final String sort;
  private final String sortDir;
  private final Date fromDate;
  private final Date toDate;
   private final String filter;

  public List<SearchCriteria> getCriterion() {
    if (!hasQuery()) {
      return Collections.emptyList();
    }

    final List<SearchCriteria> params = new ArrayList<>();
    final Pattern pattern = Pattern.compile(String.format(
        "%s(%s)%s,",
        "([\\w-. ]+)", SearchOperation.getOperationSet(), "([\\w-. ]+)"));
    final Matcher matcher = pattern.matcher(query + ",");

    while (matcher.find()) {
      params.add(
          new SearchCriteria(matcher.group(1), SearchOperation.getOperation(matcher.group(2)),
              matcher.group(3)));
    }
    return params;
  }

  public List<SearchCriteria> getFilters(){
    if (!hasFilter()) {
      return Collections.emptyList();
    }

    final List<SearchCriteria> params = new ArrayList<>();
    final Pattern pattern = Pattern.compile(String.format(
        "%s(%s)%s,",
        "([\\w-. ]+)", SearchOperation.getOperationSet(), "([\\w-. ]+)"));
    final Matcher matcher = pattern.matcher(filter + ",");

    while (matcher.find()) {
      params.add(
          new SearchCriteria(matcher.group(1), SearchOperation.getOperation(matcher.group(2)),
              matcher.group(3)));
    }
    return params;
  }

  public boolean hasQuery() {
    return StringUtils.hasText(query);
  }
  public boolean hasFilter() {
    return StringUtils.hasText(filter);
  }
}
