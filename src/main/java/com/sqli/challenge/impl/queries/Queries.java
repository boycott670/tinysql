package com.sqli.challenge.impl.queries;

import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import com.sqli.challenge.impl.Tinymysql;

public enum Queries
{
  SHOW_TABLES(Pattern.compile("^show tables$", Pattern.CASE_INSENSITIVE), ShowTablesQuery::new);
  
  private final Pattern queryPattern;
  
  private final Supplier<? extends Query> correspondingQuerySupplier;

  private Queries(Pattern queryPattern, Supplier<? extends Query> correspondingQuerySupplier)
  {
    this.queryPattern = queryPattern;
    this.correspondingQuerySupplier = correspondingQuerySupplier;
  }
  
  public List<String> execute(final Tinymysql tinymysql)
  {
    return correspondingQuerySupplier.get().executeQuery(tinymysql);
  }
  
  public boolean matches(final String query)
  {
    return queryPattern.matcher(query).matches();
  }
}
