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
}
