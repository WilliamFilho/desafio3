package com.wnet.desafio3.service.exceptions;
//não exige que tenha Try Catch (RunTimeException)
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
