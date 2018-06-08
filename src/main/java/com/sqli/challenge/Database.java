package com.sqli.challenge;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Database
{
  private Map<String, Table> tables;

  public Database(String name)
  {
    tables = new HashMap<>();
  }

  public Map<String, Table> getTables()
  {
    return Collections.unmodifiableMap(tables);
  }

  public void addTable(String tableName)
  {
    tables.put(tableName, new Table());
  }
}
