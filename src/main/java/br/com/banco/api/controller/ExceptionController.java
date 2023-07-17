package br.com.banco.api.controller;

import br.com.banco.api.controller.response.HttpResponse;
import br.com.banco.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NotFoundByNameException.class)
    public HttpResponse<ExceptionDetails> handleNotFoundByNameException(NotFoundByNameException ex) {
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

    @ExceptionHandler(NotFoundByIdException.class)
    public HttpResponse<ExceptionDetails> handleNotFoundByIdException(NotFoundByIdException ex) {
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

    @ExceptionHandler(NotFoundByOperatorEmailException.class)
    public HttpResponse<ExceptionDetails> handleNotFoundByEmailException(NotFoundByOperatorEmailException ex) {
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

    @ExceptionHandler(DateException.class)
    public HttpResponse<ExceptionDetails> handlerDateException(DateException ex) {
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

    @ExceptionHandler(NotFoundByCreationDateException.class)
    public HttpResponse<ExceptionDetails> handlerNotFoundByCreationDateException(NotFoundByCreationDateException ex) {
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