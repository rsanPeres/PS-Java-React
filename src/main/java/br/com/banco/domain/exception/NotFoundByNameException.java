package br.com.banco.domain.exception;

public class NotFoundByNameException extends RuntimeException {
    public NotFoundByNameException(String message) {
        super(message);
    }
}
