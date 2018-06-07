package com.sqli.challenge;

import java.util.Collections;
import java.util.List;

import com.sqli.challenge.impl.QueryMatcherFactory;
import com.sqli.challenge.impl.Tinymysql;

public class TinymysqlFacade
{
  private final Tinymysql tinymysql;

  public TinymysqlFacade()
  {
    tinymysql = new Tinymysql();
  }

  //  create database statement used as a combination of (create + use) statements at the same time
  public void createDatabase(String database)
  {
    tinymysql.createDatabase(database);
  }

  public List<String> execute(String query)
  {
    return QueryMatcherFactory.getQueryMatcher(query)
        .map(queryMatcher -> queryMatcher.execute(tinymysql))
        .orElse(Collections.emptyList());
  }
}
