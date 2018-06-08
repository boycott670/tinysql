package com.sqli.challenge;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TableRow
{
  private Map<String, String> content;
  
  public TableRow()
  {
    content = new HashMap<>();
  }
  
  public void addCell(String column, String value)
  {
    content.put(column, value);
  }
  
  public Map<String, String> getContent()
  {
    return Collections.unmodifiableMap(content);
  }
}
