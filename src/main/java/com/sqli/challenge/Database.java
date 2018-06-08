package com.sqli.challenge;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Database
{
  private String name;

  private Set<Table> tables;

  public Database(String name)
  {
    this.name = name;

    tables = new HashSet<>();
  }

  public Map<String, Table> getTables()
  {
    return Collections.unmodifiableMap(
        tables.stream()
            .collect(Collectors.toMap(Table::getName, Function.identity()))
    );
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
