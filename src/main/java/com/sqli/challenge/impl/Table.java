package com.sqli.challenge.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

final class Table
{
  private final String[] columns;
  
  private final ToIntFunction<String> correspondingColumnIndex;
  
  private final Collection<Object[]> rows;
  
  Table(final String[] columns)
  {
    this.columns = columns;
    
    final List<String> columnsAsLowerCase = Collections.unmodifiableList(
        Arrays.stream(columns)
          .map(String::toLowerCase)
          .collect(Collectors.toList())
    );
    
    correspondingColumnIndex = selectedColumn -> columnsAsLowerCase.indexOf(selectedColumn.toLowerCase());
    
    rows = new ArrayList<>();
  }
  
  List<String> selectFrom(final String[] columnsToSelect)
  {
    final Function<Object, String> cellPrinter = cellValue ->
        Optional.ofNullable(cellValue)
          .map(Object::toString)
          .orElse("NULL")
    ;
    
    Function<Object[], String> rowToString = row ->
        Arrays.stream(columnsToSelect)
          .mapToInt(correspondingColumnIndex)
          .mapToObj(index -> row[index])
          .map(cellPrinter)
          .collect(Collectors.joining(","))
    ;

    return rows.stream()
        .map(rowToString)
        .collect(Collectors.toList());
  }
  
  void insertInto(final String[] selectedColumns, final Object[] correspondingValues)
  {
    final Object[] rowToInsert = new Object[columns.length];
    
    for (int indexOfSelectedColumn = 0 ; indexOfSelectedColumn < selectedColumns.length ; indexOfSelectedColumn++)
    {
      final String selectedColumn = selectedColumns[indexOfSelectedColumn];
      
      final int insertionIndex = correspondingColumnIndex.applyAsInt(selectedColumn);
      
      rowToInsert[insertionIndex] = correspondingValues[indexOfSelectedColumn];
    }
    
    rows.add(rowToInsert);
  }
}
