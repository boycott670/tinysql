package com.sqli.challenge.strategies;

import java.util.List;

import com.sqli.challenge.Database;

public interface Strategy
{
  List<String> execute(String query, Database database);
}
