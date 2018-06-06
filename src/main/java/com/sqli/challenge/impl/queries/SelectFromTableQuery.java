package com.sqli.challenge.impl.queries;

import java.util.Arrays;
import java.util.List;

import com.sqli.challenge.impl.Tinymysql;

public final class SelectFromTableQuery implements Query
{
  
  private String[] columnsToSelect;
  private String tableName;

  @Override
  public void setQueryParameters(String[] queryParameters)
  {
    columnsToSelect = Arrays.copyOfRange(queryParameters, 0, queryParameters.length - 1);
    
    tableName = queryParameters[queryParameters.length - 1];
  }

  @Override
  public List<String> executeQuery(Tinymysql tinymysql)
  {
    return tinymysql.selectFromTable(tableName, columnsToSelect);
  }

  

}
