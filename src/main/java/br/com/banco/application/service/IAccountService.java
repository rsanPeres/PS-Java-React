package br.com.banco.application.service;

import br.com.banco.application.dto.AccountDto;
import br.com.banco.application.dto.BankStatementDto;
import br.com.banco.application.dto.TransactionDto;

import java.util.List;

public interface IAccountService {
    AccountDto save(AccountDto dto);
    AccountDto getById(Long id);
    AccountDto getByOperatorEmail(String emailOperator);
    AccountDto getByOperatorName(String operatorName);
    List<TransactionDto> getTransactions(String nameOperator);
    AccountDto addBankStatements(BankStatementDto bankStatementDto, String operatorEmail);
    AccountDto addTransaction(TransactionDto transactionDto, String operatorEmail);

}
