package com.sqli.challenge.impl;

import java.util.Arrays;
import java.util.Optional;

public final class QueryMatcherFactory
{
  public static Optional<QueryMatcher> getQueryMatcher(final String query)
  {
    return Arrays.stream(QueryMatcher.values())
        .filter(queryMatcher -> queryMatcher.matches(query))
        .findFirst();
  }
}
