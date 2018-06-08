package com.sqli.challenge.strategies;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.sqli.challenge.Database;
import com.sqli.challenge.SqlFacadeException;
import com.sqli.challenge.Table;
import com.sqli.challenge.TableRow;

public class SelectTableStrategy implements Strategy
{
  
  private static Pattern queryPattern = Pattern.compile("^select (?<columnsNames>.+) from (?<tableName>.+)$");
  
  private Table getTargetTable(String tableName, Database database)
  {
    Table targetTable = database.getTables().get(tableName);
    
    return Optional.ofNullable(targetTable)
        .orElseThrow(() -> new SqlFacadeException("Table not found."));
  }
  
  private String selectTableRow(String[] columnsToSelect, TableRow tableRow)
  {
    return Arrays.stream(columnsToSelect)
        .map(tableRow.getContent()::get)
        .collect(Collectors.joining(","));
  }

  @Override
  public List<String> execute(String query, Database database)
  {
    Matcher matcher = queryPattern.matcher(query);
    
    if (matcher.find())
    {
      Table tableToSelectFrom = getTargetTable(matcher.group("tableName"), database);
      
      String[] columnsToSelect = matcher.group("columnsNames").replaceAll("\\s+", "").split(",");
      
      return tableToSelectFrom.getRows()
          .stream()
          .map(row -> selectTableRow(columnsToSelect, row))
          .collect(Collectors.toList());
    }
    
    return null;
  }

}
