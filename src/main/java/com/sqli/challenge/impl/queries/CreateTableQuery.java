package com.sqli.challenge.impl.queries;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.sqli.challenge.impl.Tinymysql;

public final class CreateTableQuery implements Query
{
  private String tableName;
  
  private String[] columns;
  
  @Override
  public void setQueryParameters(String[] queryParameters)
  {
    tableName = queryParameters[0];
    
    final String rawColumns = queryParameters[1];
    
    columns = Arrays.stream(rawColumns.split(","))
        .map(columnDefinition -> columnDefinition.split(" ")[0])
        .toArray(String[]::new);
  }

  @Override
  public List<String> executeQuery(Tinymysql tinymysql)
  {
    tinymysql.createTable(tableName, columns);
    
    return Collections.emptyList();
  }

}
