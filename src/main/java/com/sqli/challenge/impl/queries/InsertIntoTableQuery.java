package com.sqli.challenge.impl.queries;

import java.util.Collections;
import java.util.List;

import com.sqli.challenge.impl.Tinymysql;

public final class InsertIntoTableQuery implements Query
{
  
  private String tableName;
  private String[] selectedColumns;
  private Object[] correspondingValues;

  @Override
  public void setQueryParameters(String[] queryParameters)
  {
    tableName = queryParameters[0];
    
    selectedColumns = queryParameters[1].split(",");
    
    correspondingValues = queryParameters[2].split(",");
  }

  @Override
  public List<String> executeQuery(Tinymysql tinymysql)
  {
    tinymysql.insertIntoTable(tableName, selectedColumns, correspondingValues);
    
    return Collections.emptyList();
  }

}
