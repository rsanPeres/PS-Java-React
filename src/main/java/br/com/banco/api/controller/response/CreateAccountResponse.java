package br.com.banco.api.controller.response;

import br.com.banco.domain.entity.Operator;

import java.math.BigDecimal;

public class CreateAccountResponse {
    private Long id;
    private Operator operator;
    private BigDecimal accountBalance;
}

