package com.sqli.challenge.impl.queries;

import java.util.List;

import com.sqli.challenge.impl.Tinymysql;

public final class ShowTablesQuery implements Query
{

  @Override
  public List<String> executeQuery(Tinymysql tinymysql)
  {
    return tinymysql.showTables();
  }

}
