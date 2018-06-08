package com.sqli.challenge.strategies;

import java.util.ArrayList;
import java.util.List;

import com.sqli.challenge.Database;
import com.sqli.challenge.SqlFacadeException;

public class ShowTablesStrategy implements Strategy
{

  @Override
  public List<String> execute(String query, Database database)
  {
    if (database == null)
    {
      throw new SqlFacadeException("No Database selected.");
    }
    
    return new ArrayList<>(database.getTables().keySet());
  }

}
