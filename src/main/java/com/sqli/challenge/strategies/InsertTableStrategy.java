package com.sqli.challenge.strategies;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import com.sqli.challenge.Database;
import com.sqli.challenge.Table;
import com.sqli.challenge.TableRow;

public class InsertTableStrategy implements Strategy
{
  
  private static Pattern queryPattern = Pattern.compile("insert into (?<tableName>.+) (?<columnsNames>.+) values\\((?<columnsValues>.+)\\)");
  
  private TableRow createNewFilledRow(String[] columns, String[] values)
  {
    TableRow row = new TableRow();
    
    IntStream.range(0, columns.length)
        .forEach(index -> row.addCell(columns[index], values[index]));
    
    return row;
  }

  @Override
  public List<String> execute(String query, Database database)
  {
    Matcher matcher = queryPattern.matcher(query);
    
    if (matcher.find())
    {
      Table table = database.getTables().get(matcher.group("tableName"));
      
      String[] columns = matcher.group("columnsNames").split(",");
      
      String[] values = matcher.group("columnsValues").split(",");
      
      table.addRow(createNewFilledRow(columns, values));
    }
    
    return null;
  }

}
