package com.sqli.challenge.strategies;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sqli.challenge.Database;

public class CreateTableStrategy implements Strategy
{
  
  private static Pattern queryPattern = Pattern.compile("^CREATE TABLE (?<tableName>.+) \\((.+)\\)$");

  @Override
  public List<String> execute(String query, Database database)
  {
    Matcher queryPatternMatcher = queryPattern.matcher(query);
    
    if (queryPatternMatcher.find())
    {
      database.addTable(queryPatternMatcher.group("tableName"));
    }
    
    return null;
  }

}
