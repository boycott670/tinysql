package com.sqli.challenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TinymysqlTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testANewDatabaseHasNoTables() {
        TinymysqlFacade sqlFacade = new TinymysqlFacade();
        sqlFacade.createDatabase("db1");
        List<String> results = sqlFacade.execute("show tables");
        assertTrue(results.isEmpty());
    }

    @Test
    public void testADatabseMustBeInUseFirst() {
        thrown.expect(SqlFacadeException.class);
        thrown.expectMessage("No Database selected.");
        TinymysqlFacade sqlFacade = new TinymysqlFacade();
        List<String> results = sqlFacade.execute("show tables");
    }

    @Test
    public void testDisplayCreatedTables() {
        TinymysqlFacade sqlFacade = new TinymysqlFacade();
        sqlFacade.createDatabase("db1");
        sqlFacade.execute("CREATE TABLE Author (LastName varchar,FirstName varchar)");
        sqlFacade.execute("CREATE TABLE Book (Title varchar)");
        List<String> results = sqlFacade.execute("show tables");
        List<String> expected = Arrays.asList("Book", "Author");
        Collections.sort(results);
        Collections.sort(expected);
        assertEquals(results, expected);
    }

    @Test
    public void testNoTable() {
        TinymysqlFacade sqlFacade = new TinymysqlFacade();
        thrown.expect(SqlFacadeException.class);
        thrown.expectMessage("Table not found.");
        sqlFacade.createDatabase("db1");
        List<String> results = sqlFacade.execute("select LastName, FirstName from Author");
    }

    @Test
    public void testEmptyTableContent() {
        TinymysqlFacade sqlFacade = new TinymysqlFacade();
        sqlFacade.createDatabase("db1");
        sqlFacade.execute("CREATE TABLE Author (LastName varchar, FirstName varchar)");
        List<String> results = sqlFacade.execute("select LastName, FirstName from Author");
        assertTrue(results.isEmpty());
    }

    @Test
    public void testSelectASpecificColumnsFromTable() {
        TinymysqlFacade sqlFacade = new TinymysqlFacade();
        sqlFacade.createDatabase("db1");
        sqlFacade.execute("CREATE TABLE Author (LastName varchar,FirstName varchar)");
        sqlFacade.execute("insert into Author LastName,FirstName values(\"Asimov\",\"Isaac\")");
        sqlFacade.execute("insert into Author LastName,FirstName values(\"Heinlein\",\"Robert\")");
        List<String> results = sqlFacade.execute("select LastName,FirstName from Author");
        List<String> expected = Arrays.asList("\"Asimov\",\"Isaac\"", "\"Heinlein\",\"Robert\"");
        Collections.sort(results);
        Collections.sort(expected);
        assertEquals(results, expected);
    }

    @Test
    public void testSelectASpecificSwitchedColumnsFromTable() {
        TinymysqlFacade sqlFacade = new TinymysqlFacade();
        sqlFacade.createDatabase("db1");
        sqlFacade.execute("CREATE TABLE Author (LastName varchar,FirstName varchar)");
        sqlFacade.execute("insert into Author LastName,FirstName values(\"Asimov\",\"Isaac\")");
        sqlFacade.execute("insert into Author LastName,FirstName values(\"Heinlein\",\"Robert\")");
        List<String> results = sqlFacade.execute("select FirstName,LastName from Author");
        List<String> expected = Arrays.asList("\"Isaac\",\"Asimov\"", "\"Robert\",\"Heinlein\"");
        Collections.sort(results);
        Collections.sort(expected);
        assertEquals(results, expected);
    }
}
