package com.sqli.challenge.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    return currentlySelectedDatabase.showTables();
  }
}
