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
}
