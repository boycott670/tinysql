package com.sqli.challenge;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.sqli.challenge.impl.Tinymysql;
import com.sqli.challenge.impl.queries.Queries;

public class TinymysqlFacade {
    private final Tinymysql tinymysql;
    
    public TinymysqlFacade()
    {
      tinymysql = new Tinymysql();
    }
  
    public void createDatabase(String database) {
      tinymysql.createDatabase(database);
    }

    public List<String> execute(String queryString) {
      return Arrays.stream(Queries.values())
        .filter(query -> query.matches(queryString))
        .map(query -> query.execute(tinymysql))
        .findFirst()
        .orElse(Collections.emptyList());
    }
}
