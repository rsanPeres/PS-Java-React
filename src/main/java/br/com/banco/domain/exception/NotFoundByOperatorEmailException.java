package br.com.banco.domain.exception;

public class NotFoundByOperatorEmailException extends RuntimeException {
    public NotFoundByOperatorEmailException(String message) {
        super(message);
    }
}
