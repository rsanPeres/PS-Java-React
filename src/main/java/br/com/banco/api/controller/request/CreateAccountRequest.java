package br.com.banco.api.controller.request;

import br.com.banco.domain.entity.Operator;

import java.math.BigDecimal;

public class CreateAccountRequest {
    private Long operator_id;
    private BigDecimal accountBalance;
}
