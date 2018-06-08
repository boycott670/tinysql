package com.sqli.challenge;

import java.util.Objects;

public class Table
{
  private String name;

  public Table(String name)
  {
    this.name = name;
  }

  public String getName()
  {
    return name;
  }
  
  @Override
  public boolean equals(Object other)
  {
    return other instanceof Table ? Objects.equals(name, ((Table)other).name) : false;
  }
}
