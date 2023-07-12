package br.com.banco.application.dto;

import br.com.banco.domain.entity.BankStatement;
import br.com.banco.domain.entity.Operator;
import br.com.banco.domain.entity.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class AccountDto {
    public Long id;
    private Operator operator;
    private BigDecimal accountBalance;
    public List<BankStatement> bankStatements;
    public List<Transaction> transactionsCarriedOut;
    public List<Transaction> incomingTransactions;
    public LocalDate createdAt;
}
