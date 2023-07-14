package br.com.banco.api.controller.response;

import br.com.banco.domain.entity.Account;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BankStatementResponse {
    public Long id;
    public Long account_id;
    public LocalDate beginning;
    public LocalDate end;
    public BigDecimal totalBalancePeriod;
    public BigDecimal currentBalance;
}
