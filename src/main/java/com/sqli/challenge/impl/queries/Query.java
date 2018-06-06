package com.sqli.challenge.impl.queries;

import java.util.List;

import com.sqli.challenge.impl.Tinymysql;

public interface Query
{
  List<String> executeQuery(final Tinymysql tinymysql);
}
