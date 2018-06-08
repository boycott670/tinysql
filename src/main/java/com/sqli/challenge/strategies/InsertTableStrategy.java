package com.sqli.challenge.strategies;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sqli.challenge.Database;
import com.sqli.challenge.Table;

public class InsertTableStrategy implements Strategy
{
  
  private static Pattern queryPattern = Pattern.compile("insert into (?<tableName>.+) (?<columnsNames>.+) values\\((?<columnsValues>.+)\\)");

  @Override
  public List<String> execute(String query, Database database)
  {
    Matcher matcher = queryPattern.matcher(query);
    
    matcher.find();
    
    Table table = database.getTables().get(matcher.group("tableName"));
    
    String[] columns = matcher.group("columnsNames").split(",");
    
    String[] values = matcher.group("columnsValues").split(",");
    
    table.addRow(columns, values);
    
    return null;
  }

}
