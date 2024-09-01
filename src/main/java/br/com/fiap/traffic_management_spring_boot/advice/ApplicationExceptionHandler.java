package br.com.fiap.traffic_management_spring_boot.advice;


import br.com.fiap.traffic_management_spring_boot.exception.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException error){
        Map<String, String> errorMap = new HashMap<>();
        List<FieldError> fieldErrorList = error.getBindingResult().getFieldErrors();
        for(FieldError fieldError : fieldErrorList){
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFound(NotFoundException error){
        return error.getMessage();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handlePersistedDataIntegrity(DataIntegrityViolationException error){
        return error.getMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public String handleInternalServerError(RuntimeException error){
        return error.getMessage();
    }
}
