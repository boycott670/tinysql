package com.sqli.challenge.impl.queries;

import java.util.List;

import com.sqli.challenge.impl.Tinymysql;

public interface Query
{
  default void setQueryParameters(final String[] queryParameters)
  {
    // default implementation for 0-args statements (e.g: `show tables`)
  }
  
  List<String> executeQuery(final Tinymysql tinymysql);
}
