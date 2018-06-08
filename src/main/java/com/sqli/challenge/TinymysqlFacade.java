package com.sqli.challenge;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TinymysqlFacade
{
  private Set<Database> databases;
  
  private Database currentDatabase;

  public TinymysqlFacade()
  {
    databases = new HashSet<>();
  }

  public void createDatabase(String databaseName)
  {
    databases.add(currentDatabase = new Database(databaseName));
  }

  public List<String> execute(String query)
  {
    return StrategyFactory.getStrategy(query).execute(query, currentDatabase);
  }
}
