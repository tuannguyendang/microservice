package com.dangtuan.order.repository.specification.builder;

import com.dangtuan.order.dto.UserSession;
import com.dangtuan.order.dto.filter.SearchCriteria;
import com.dangtuan.order.entity.Order;
import com.dangtuan.order.repository.specification.OrderSpecification;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public class OrderSpecificationBuilder {

  private final String fullTextSearch;
  private final List<SearchCriteria> params;
  private final UserSession session;

  private static final String LIKE_WILD_CARD =  "%";

  public OrderSpecificationBuilder(final String fullTextSearch,
      final List<SearchCriteria> params, final List<SearchCriteria> filters, final UserSession session) {
    this.fullTextSearch = fullTextSearch;
    this.params = params;
    this.session = session;
  }

  public Specification<Order> build() {
    Specification<Order> spec = Specification.where(
        getAttributeSpecification("tenantId", this.session)
            .and(getAttributeSpecification("deleted", Boolean.FALSE)));

    for (SearchCriteria criteria : params) {
      spec = spec.and(new OrderSpecification(criteria));
    }
    return spec;
  }

  public Specification<Order> getAttributeSpecification(final String attribute,
      final Object value) {
    return (root, query, cb) -> cb
        .equal(root.get(attribute).as(String.class), String.valueOf(value));
  }

  private static String getLikeValueField(final String fullTextSearch) {
    return new StringBuilder().append(LIKE_WILD_CARD).append(fullTextSearch).append(LIKE_WILD_CARD)
        .toString().toLowerCase();
  }
}
