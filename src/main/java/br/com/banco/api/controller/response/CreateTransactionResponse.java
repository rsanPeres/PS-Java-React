package br.com.banco.api.controller.response;

import br.com.banco.domain.entity.Account;
import br.com.banco.domain.entity.Operator;
import br.com.banco.domain.enummeration.OperationType;
import br.com.banco.domain.enummeration.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateTransactionResponse {
    public Long id;
    public Long receiver_id;
    public LocalDate date;
    public TransactionType transactionType;
    public OperationType operationType;
    public Long issuer_id;
    public BigDecimal value;
    public Long account_id;
}
