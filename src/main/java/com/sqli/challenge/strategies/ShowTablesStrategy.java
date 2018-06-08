package com.sqli.challenge.strategies;

import java.util.List;
import java.util.stream.Collectors;

import com.sqli.challenge.Database;
import com.sqli.challenge.SqlFacadeException;
import com.sqli.challenge.Table;

public class ShowTablesStrategy implements Strategy
{

  @Override
  public List<String> execute(String query, Database database)
  {
    if (database == null)
    {
      throw new SqlFacadeException("No Database selected.");
    }
    
    return database.getTables()
        .stream()
        .map(Table::getName)
        .collect(Collectors.toList());
  }

}
