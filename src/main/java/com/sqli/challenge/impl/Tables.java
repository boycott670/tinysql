package com.sqli.challenge.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sqli.challenge.SqlFacadeException;

final class Tables
{
  private final Map<String, Table> tables;
  
  Tables()
  {
    tables = new LinkedHashMap<>();
  }
  
  List<String> showTables()
  {
    return tables.keySet()
        .stream()
        .sorted()
        .collect(Collectors.toList());
  }
  
  void createTable(final String tableName, final String[] tableColumns)
  {
    tables.put(tableName, new Table(tableColumns));
  }
  
  private Table fetchSelectedTable(final String tableName)
  {
    return Optional.ofNullable(tables.get(tableName)).orElseThrow(() -> new SqlFacadeException("Table not found."));
  }
  
  List<String> selectFromTable(final String tableName, final String[] columnsToSelect)
  {
    final Table correspondingTable = fetchSelectedTable(tableName);
    
    return correspondingTable.selectFrom(columnsToSelect);
  }
  
  void insertIntoTable(final String tableName, final String[] selectedColumns, final Object[] correspondingValues)
  {
    final Table correspondingTable = fetchSelectedTable(tableName);
    
    correspondingTable.insertInto(selectedColumns, correspondingValues);
  }
}
