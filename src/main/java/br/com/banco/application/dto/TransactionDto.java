package br.com.banco.application.dto;

import br.com.banco.domain.entity.Account;
import br.com.banco.domain.entity.Operator;
import br.com.banco.domain.enummeration.OperationType;
import br.com.banco.domain.enummeration.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionDto {
    public Long id;
    public Operator receiver;
    public LocalDate date;
    public TransactionType transactionType;
    public OperationType operationType;
    public Operator issuer;
    public BigDecimal value;
    public Account account;
}
