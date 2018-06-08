package com.sqli.challenge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Table
{
  private Collection<TableRow> rows;

  public Table()
  {
    rows = new ArrayList<>();
  }
  
  public Collection<TableRow> getRows()
  {
    return Collections.unmodifiableCollection(rows);
  }
  
  public void addRow(TableRow row)
  {
    rows.add(row);
  }
}
