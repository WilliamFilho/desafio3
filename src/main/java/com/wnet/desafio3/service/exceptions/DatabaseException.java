package com.wnet.desafio3.service.exceptions;
//não exige que tenha Try Catch (RuntimeException)
public class DatabaseException extends RuntimeException{
    public DatabaseException(String message) {
        super(message);
    }
}
