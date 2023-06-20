package com.wnet.desafio3.service.exceptions;
//Não exige que tenha Try Catch (RunTimeException)
//Exceção personalizada para recurso não encontrado...
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
