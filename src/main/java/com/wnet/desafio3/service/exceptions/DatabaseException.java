package com.wnet.desafio3.service.exceptions;
//Não exige que tenha Try Catch (RuntimeException)
//Cria exceção personalizada para error referencia a dados(DataBase)...
public class DatabaseException extends RuntimeException{
    public DatabaseException(String message) {
        super(message);
    }
}
