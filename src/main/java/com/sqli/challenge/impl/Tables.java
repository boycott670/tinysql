package com.sqli.challenge.impl;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

final class Tables
{
  private final Map<String, Table> tables;
  
  Tables()
  {
    tables = new LinkedHashMap<>();
  }
  
  List<String> showTables()
  {
    final Iterator<String> reverseIteratorOverTablesNames = new ArrayDeque<>(tables.keySet()).descendingIterator();
    
    final Iterable<String> correspondingIterable = () -> reverseIteratorOverTablesNames;
    
    return StreamSupport.stream(correspondingIterable.spliterator(), false).collect(Collectors.toList());
  }
}
