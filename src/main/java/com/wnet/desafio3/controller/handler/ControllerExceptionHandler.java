package com.wnet.desafio3.controller.handler;

import com.wnet.desafio3.dto.errors.CustomError;
import com.wnet.desafio3.dto.errors.ValidationError;
import com.wnet.desafio3.service.exceptions.DatabaseException;
import com.wnet.desafio3.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;


//Já tratar os principais errors da aplicação...
//Intercepta as exceções e as personaliza...
@ControllerAdvice
public class ControllerExceptionHandler {
    //Recurso não encontrado...
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    //Entidade não encontrada...
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(EntityNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    //Erros de dados (ex.: violação de integridade referencial)
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomError> dataBase(DatabaseException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    //Interceptar e trata as validações (campos...)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError err = new ValidationError(Instant.now(), status.value(), "Dados inválidos!", request.getRequestURI());
        for (FieldError f: e.getBindingResult().getFieldErrors()) {
            err.addError(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(err);
    }
}
