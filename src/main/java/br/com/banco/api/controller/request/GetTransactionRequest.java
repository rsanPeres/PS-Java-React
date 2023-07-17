package br.com.banco.api.controller.request;

import java.time.LocalDate;

public class GetTransactionRequest {
    public LocalDate start;
    public LocalDate end;
    public String firstName;
    public String lastName;
}
