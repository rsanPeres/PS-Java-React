package br.com.banco.api.controller.request;

import java.math.BigDecimal;

public class CreateUserRequest {
    public String firstName;
    public String lastName;
    public String cpf;
    public String email;
    public BigDecimal income;
    public String password;
}
