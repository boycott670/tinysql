package com.sqli.challenge.impl;

import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import com.sqli.challenge.impl.queries.CreateTableQuery;
import com.sqli.challenge.impl.queries.InsertIntoTableQuery;
import com.sqli.challenge.impl.queries.Query;
import com.sqli.challenge.impl.queries.SelectFromTableQuery;
import com.sqli.challenge.impl.queries.ShowTablesQuery;

public enum QueryMatcher
{
  SHOW_TABLES("^show tables$", ShowTablesQuery::new),
  
  CREATE_TABLE("^create table (.+) \\((.+)\\)$", CreateTableQuery::new),
  
  SELECT_FROM_TABLE("^select (.+) from (.+)$", SelectFromTableQuery::new),
  
  INSERT_INTO_TABLE("^insert into (.+) (.+) values\\((.+)\\)$", InsertIntoTableQuery::new);
  
  private final Pattern queryPattern;

  private final Supplier<? extends Query> correspondingQuerySupplier;

  private Matcher queryPatternMatcher;

  private QueryMatcher(String queryPattern, Supplier<? extends Query> correspondingQuerySupplier)
  {
    this.queryPattern = Pattern.compile(queryPattern, Pattern.CASE_INSENSITIVE);
    
    this.correspondingQuerySupplier = correspondingQuerySupplier;
  }

  public List<String> execute(final Tinymysql tinymysql)
  {
    final String[] queryParameters = IntStream.rangeClosed(1, queryPatternMatcher.groupCount())
        .mapToObj(queryPatternMatcher::group)
        .toArray(String[]::new);
    
    final Query correspondingQuery = correspondingQuerySupplier.get();

    correspondingQuery.setQueryParameters(queryParameters);

    return correspondingQuery.executeQuery(tinymysql);
  }

  boolean matches(final String inputQuery)
  {
    queryPatternMatcher = queryPattern.matcher(inputQuery);

    return queryPatternMatcher.matches();
  }
}
