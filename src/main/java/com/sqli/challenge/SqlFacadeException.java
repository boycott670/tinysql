package com.sqli.challenge;

public class SqlFacadeException extends RuntimeException{
    public SqlFacadeException() {
        super();
    }
    public SqlFacadeException(String s) {
        super(s);
    }
}
