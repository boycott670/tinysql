package com.sqli.challenge.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.sqli.challenge.SqlFacadeException;

final class Databases
{
  private final Map<String, Database> databases;
  
  private Database currentlySelectedDatabase;
  
  Databases()
  {
    databases = new LinkedHashMap<>();
  }
  
  void createDatabase(final String databaseName)
  {
    databases.put(databaseName, currentlySelectedDatabase = new Database());
  }
  
  List<String> showTables()
  {
    if (currentlySelectedDatabase == null)
    {
      throw new SqlFacadeException("No Database selected.");
    }
    
    return currentlySelectedDatabase.showTables();
  }
  
  void createTable(final String tableName, final String[] tableColumns)
  {
    currentlySelectedDatabase.createTable(tableName, tableColumns);
  }
  
  List<String> selectFromTable(final String tableName, final String[] columnsToSelect)
  {
    return currentlySelectedDatabase.selectFromTable(tableName, columnsToSelect);
  }
}
