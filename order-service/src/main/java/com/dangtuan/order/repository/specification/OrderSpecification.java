package com.dangtuan.order.repository.specification;

import com.dangtuan.order.dto.filter.SearchCriteria;
import com.dangtuan.order.entity.Order;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class OrderSpecification implements Specification<Order> {

  private static final String WILDCARD = "%";
  private SearchCriteria criteria;

  public OrderSpecification(final SearchCriteria criteria) {
    this.criteria = criteria;
  }

  public static String containsLowerCase(final String searchField) {
    return WILDCARD + searchField.toLowerCase() + WILDCARD;
  }

  @Override
  public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query,
      CriteriaBuilder criteriaBuilder) {
    switch (criteria.getOperation()) {
      case EQUALITY:
        return criteriaBuilder
            .equal(criteriaBuilder.lower(root.get(criteria.getKey()).as(String.class)),
                criteria.getValue().toString().toLowerCase());
      case NEGATION:
        return criteriaBuilder
            .notEqual(criteriaBuilder.lower(root.get(criteria.getKey()).as(String.class)),
                criteria.getValue().toString().toLowerCase());
      case GREATER_THAN:
        return criteriaBuilder
            .greaterThan(root.get(criteria.getKey()).as(String.class),
                criteria.getValue().toString());
      case GREATER_THAN_OR_EQUAL_TO:
        return criteriaBuilder
            .greaterThanOrEqualTo(root.get(criteria.getKey()).as(String.class),
                criteria.getValue().toString());
      case LESS_THAN:
        return criteriaBuilder
            .lessThan(root.get(criteria.getKey()).as(String.class), criteria.getValue().toString());
      case LESS_THAN_OR_EQUAL_TO:
        return criteriaBuilder
            .lessThanOrEqualTo(root.get(criteria.getKey()).as(String.class),
                criteria.getValue().toString());
      case LIKE:
        return criteriaBuilder
            .like(criteriaBuilder.lower(root.get(criteria.getKey()).as(String.class)),
                containsLowerCase(criteria.getValue().toString()));
      default:
        return null;
    }
  }
}
