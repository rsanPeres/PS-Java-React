package br.com.banco.domain.exception;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
public class ExceptionDetails {
    public  String title;
    public LocalDateTime timestamp;
    public int status;
    public String  exception;
    public Map<String, String> datails;
}
