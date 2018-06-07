package com.sqli.challenge.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.sqli.challenge.SqlFacadeException;

final class Databases
{
  private final Map<String, Database> databases;
  
  private String currentlySelectedDatabaseName;
  
  Databases()
  {
    databases = new HashMap<>();
    
    currentlySelectedDatabaseName = null;
  }
  
  void createDatabase(final String databaseName)
  {
    databases.put(currentlySelectedDatabaseName = databaseName, new Database());
  }
  
  private Database getCurrentlySelectedDatabase()
  {
    return Optional.ofNullable(currentlySelectedDatabaseName)
        .map(databases::get)
        .orElseThrow(() -> new SqlFacadeException("No Database selected."));
  }
  
  List<String> showTables()
  {
    return getCurrentlySelectedDatabase().showTables();
  }
  
  void createTable(final String tableName, final String[] tableColumns)
  {
    getCurrentlySelectedDatabase().createTable(tableName, tableColumns);
  }
  
  List<String> selectFromTable(final String tableName, final String[] columnsToSelect)
  {
    return getCurrentlySelectedDatabase().selectFromTable(tableName, columnsToSelect);
  }
  
  void insertIntoTable(final String tableName, final String[] selectedColumns, final Object[] correspondingValues)
  {
    getCurrentlySelectedDatabase().insertIntoTable(tableName, selectedColumns, correspondingValues);
  }
}
