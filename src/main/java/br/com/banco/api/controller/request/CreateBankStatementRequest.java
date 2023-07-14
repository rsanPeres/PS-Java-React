package br.com.banco.api.controller.request;

import java.time.LocalDate;

public class CreateBankStatementRequest {
    public LocalDate start;
    public LocalDate end;
    public String nameOperator;
}
