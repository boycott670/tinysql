package com.sqli.challenge.impl;

import java.util.List;

public final class Tinymysql
{
  private final Databases databases;
  
  public Tinymysql()
  {
    databases = new Databases();
  }
  
  // create database statement used as a combination of (create + use) statements at the same time
  public void createDatabase(final String databaseName)
  {
    databases.createDatabase(databaseName);
  }
  
  public List<String> showTables()
  {
    return databases.showTables();
  }
  
  public void createTable(final String tableName, final String[] tableColumns)
  {
    databases.createTable(tableName, tableColumns);
  }
  
  public List<String> selectFromTable(final String tableName, final String[] columnsToSelect)
  {
    return databases.selectFromTable(tableName, columnsToSelect);
  }
  
  public void insertIntoTable(final String tableName, final String[] selectedColumns, final Object[] correspondingValues)
  {
    databases.insertIntoTable(tableName, selectedColumns, correspondingValues);
  }
}
