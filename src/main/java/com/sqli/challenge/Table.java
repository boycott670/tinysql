package com.sqli.challenge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Table
{
  private String name;
  
  private Collection<Map<String, String>> rows;

  public Table(String name)
  {
    this.name = name;
    
    rows = new ArrayList<>();
  }

  public String getName()
  {
    return name;
  }
  
  public Collection<Map<String, String>> getRows()
  {
    return Collections.unmodifiableList(rows.stream()
        .map(Collections::unmodifiableMap)
        .collect(Collectors.toList()));
  }
  
  @Override
  public boolean equals(Object other)
  {
    return other instanceof Table ? Objects.equals(name, ((Table)other).name) : false;
  }
}
