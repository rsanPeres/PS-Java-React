package br.com.banco.api.controller;

import br.com.banco.api.controller.response.HttpResponse;
import br.com.banco.domain.exception.ExceptionDetails;
import br.com.banco.domain.exception.NotFoundByNameException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpResponse<ExceptionDetails> handleValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String messageError = error.getDefaultMessage();
            errors.put(fieldName, messageError);
        });

        ExceptionDetails exceptionDetails = new ExceptionDetails(
                "Bad Request",
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getClass().toString(),
                errors
        );

        return new HttpResponse<>(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), exceptionDetails);
    }

    @ExceptionHandler(DataAccessException.class)
    public HttpResponse<ExceptionDetails> handleDataAccessException(DataAccessException ex) {
        Map<String, String> details = new HashMap<>();
        details.put(ex.getCause().toString(), ex.getMessage());

        ExceptionDetails exceptionDetails = new ExceptionDetails(
                "Conflict",
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                ex.getClass().toString(),
                details
        );

        return new HttpResponse<>(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase(), exceptionDetails);
    }

    @ExceptionHandler(NotFoundByNameException.class)
    public HttpResponse<ExceptionDetails> handleNotFoundByIdException(NotFoundByNameException ex) {
        Map<String, String> details = new HashMap<>();
        details.put(ex.getCause().toString(), ex.getMessage());

        ExceptionDetails exceptionDetails = new ExceptionDetails(
                "Bad Request",
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getClass().toString(),
                details
        );

        return new HttpResponse<>(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), exceptionDetails);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public HttpResponse<ExceptionDetails> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, String> details = new HashMap<>();
        details.put(ex.getCause().toString(), ex.getMessage());

        ExceptionDetails exceptionDetails = new ExceptionDetails(
                "Bad Request",
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getClass().toString(),
                details
        );

        return new HttpResponse<>(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), exceptionDetails);
    }
}