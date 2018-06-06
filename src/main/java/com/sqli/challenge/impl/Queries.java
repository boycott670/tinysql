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

public enum Queries
{
  SHOW_TABLES(Pattern.compile("^show tables$", Pattern.CASE_INSENSITIVE), ShowTablesQuery::new),
  
  CREATE_TABLE(Pattern.compile("^create table (.+) \\((.+)\\)$", Pattern.CASE_INSENSITIVE), CreateTableQuery::new),
  
  SELECT_FROM_TABLE(Pattern.compile("^select (.+) from (.+)$", Pattern.CASE_INSENSITIVE), SelectFromTableQuery::new),
  
  INSERT_INTO_TABLE(Pattern.compile("^insert into (.+) (.+) values\\((.+)\\)$", Pattern.CASE_INSENSITIVE), InsertIntoTableQuery::new);
  
  private final Pattern queryPattern;

  private final Supplier<? extends Query> correspondingQuerySupplier;

  private String inputQuery;

  private Queries(Pattern queryPattern, Supplier<? extends Query> correspondingQuerySupplier)
  {
    this.queryPattern = queryPattern;
    
    this.correspondingQuerySupplier = correspondingQuerySupplier;
  }

  public List<String> execute(final Tinymysql tinymysql)
  {
    final Matcher matcher = queryPattern.matcher(inputQuery);

    matcher.find();

    final String[] queryParameters = IntStream.rangeClosed(1, matcher.groupCount())
        .mapToObj(matcher::group)
        .toArray(String[]::new);
    
    final Query correspondingQuery = correspondingQuerySupplier.get();

    correspondingQuery.setQueryParameters(queryParameters);

    return correspondingQuery.executeQuery(tinymysql);
  }

  public boolean matches(final String inputQuery)
  {
    this.inputQuery = inputQuery;

    return queryPattern.matcher(this.inputQuery).matches();
  }
}
