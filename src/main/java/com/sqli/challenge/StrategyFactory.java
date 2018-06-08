package com.sqli.challenge;

import com.sqli.challenge.strategies.CreateTableStrategy;
import com.sqli.challenge.strategies.InsertTableStrategy;
import com.sqli.challenge.strategies.SelectTableStrategy;
import com.sqli.challenge.strategies.ShowTablesStrategy;
import com.sqli.challenge.strategies.Strategy;

public class StrategyFactory
{
  public static Strategy getStrategy(String query)
  {
     if (query.equals("show tables"))
     {
       return new ShowTablesStrategy();
     }
     else if (query.startsWith("CREATE TABLE"))
     {
       return new CreateTableStrategy();
     }
     else if (query.startsWith("select"))
     {
       return new SelectTableStrategy();
     }
     
     return new InsertTableStrategy();
  }
}
