package com.dangtuan.order.dto.filter;

/**
 * Search operations.
 */
public enum SearchOperation {
  EQUALITY, NEGATION, GREATER_THAN, LESS_THAN, LIKE, GREATER_THAN_OR_EQUAL_TO, LESS_THAN_OR_EQUAL_TO;

  private static final String[] OPERATION_SET = {"=", "!", ">", "<", "~", ">=", "<="};

  public static SearchOperation getOperation(final String input) {
    switch (input) {
      case "=":
        return EQUALITY;
      case "!":
        return NEGATION;
      case ">":
        return GREATER_THAN;
      case "<":
        return LESS_THAN;
      case "~":
        return LIKE;
      case ">=":
        return GREATER_THAN_OR_EQUAL_TO;
      case "<=":
        return LESS_THAN_OR_EQUAL_TO;
      default:
        return null;
    }
  }

  public static String getOperationSet() {
    return String.join("|", OPERATION_SET);
  }
}
