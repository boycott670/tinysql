package com.sqli.challenge.strategies;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.sqli.challenge.Database;
import com.sqli.challenge.SqlFacadeException;
import com.sqli.challenge.Table;

public class SelectTableStrategy implements Strategy
{
  
  private static Pattern queryPattern = Pattern.compile("^select (?<columnsNames>.+) from (?<tableName>.+)$");

  @Override
  public List<String> execute(String query, Database database)
  {
    Map<String, Table> databaseTablesByName = database.getTables()
        .stream()
        .collect(Collectors.toMap(Table::getName, Function.identity()));
    
    Matcher matcher = queryPattern.matcher(query);
    
    matcher.find();
    
    Table tableToSelectFrom = databaseTablesByName.get(matcher.group("tableName"));
    
    String[] columnsToSelect = matcher.group("columnsNames").split(", ");
    
    if (tableToSelectFrom == null)
    {
      throw new SqlFacadeException("Table not found.");
    }
    
    Function<Map<String, String>, String> rowPrinter = row -> Arrays.stream(columnsToSelect)
        .map(row::get)
        .collect(Collectors.joining(","));
    
    return tableToSelectFrom.getRows()
        .stream()
        .map(rowPrinter)
        .collect(Collectors.toList());
  }

}
