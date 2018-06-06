package com.sqli.challenge.impl;

import java.util.List;

final class Database
{
  private final Tables tables;
  
  Database()
  {
    tables = new Tables();
  }
  
  List<String> showTables()
  {
    return tables.showTables();
  }
  
  void createTable(final String tableName, final String[] tableColumns)
  {
    tables.createTable(tableName, tableColumns);
  }
  
  List<String> selectFromTable(final String tableName, final String[] columnsToSelect)
  {
    return tables.selectFromTable(tableName, columnsToSelect);
  }
  
  void insertIntoTable(final String tableName, final String[] selectedColumns, final Object[] correspondingValues)
  {
    tables.insertIntoTable(tableName, selectedColumns, correspondingValues);
  }
}
