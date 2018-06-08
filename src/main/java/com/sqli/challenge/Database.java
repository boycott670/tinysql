package com.sqli.challenge;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Database
{
  private String name;

  private Set<Table> tables;

  public Database(String name)
  {
    this.name = name;

    tables = new HashSet<>();
  }

  public Set<Table> getTables()
  {
    return Collections.unmodifiableSet(tables);
  }

  public void addTable(Table table)
  {
    tables.add(table);
  }
  
  @Override
  public boolean equals(Object other)
  {
    return other instanceof Database ? Objects.equals(name, ((Database)other).name) : false;
  }
}
