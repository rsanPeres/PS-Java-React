package br.com.banco.application.dto;

import br.com.banco.domain.entity.Account;
import br.com.banco.domain.entity.BankStatement;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
public class BankStatementDto {
    public Long id;
    public Account account;
    public LocalDate beginning;
    public LocalDate end;
    public BigDecimal totalBalancePeriod;
    public BigDecimal currentBalance;

    public BankStatementDto(BankStatement statement){
        this.id = statement.getId();
        this.account = statement.getAccount();
        this.beginning = statement.getBeginning();
        this.end = statement.getEnd();
        this.totalBalancePeriod = statement.getTotalBalancePeriod();
        this.currentBalance = statement.getCurrentBalance();
    }
}
