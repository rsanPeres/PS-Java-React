package br.com.banco.domain.exception;

public class NotFoundByCreationDateException extends RuntimeException {
    public NotFoundByCreationDateException(String message) {
        super(message);
    }
}
