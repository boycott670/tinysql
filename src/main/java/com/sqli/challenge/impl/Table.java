package com.sqli.challenge.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

final class Table
{
  private final String[] columns;
  
  private final Collection<Object[]> rows;
  
  Table(final String[] columns)
  {
    this.columns = columns;
    
    rows = new ArrayList<>();
  }
  
  List<String> selectFromTable(final String[] columnsToSelect)
  {
    return null;
  }
}
