package br.com.banco.application.service.impl;

import br.com.banco.application.dto.AccountDto;
import br.com.banco.application.dto.BankStatementDto;
import br.com.banco.application.dto.TransactionDto;
import br.com.banco.application.service.IAccountService;
import br.com.banco.domain.entity.Account;
import br.com.banco.domain.entity.BankStatement;
import br.com.banco.domain.entity.Transaction;
import br.com.banco.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService {
    private final AccountRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public AccountService(AccountRepository repository, ModelMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AccountDto save(AccountDto dto) {
        repository.save(mapper.map(dto, Account.class));
        return dto;
    }

    @Override
    public AccountDto getById(Long id) {
        Account account = repository.getById(id);
        return mapper.map(account, AccountDto.class);
    }

    @Override
    public AccountDto getByOperatorEmail(String emailOperator) {
        Account account = repository.findByOperatorEmail(emailOperator);
        return mapper.map(account, AccountDto.class);
    }

    @Override
    public AccountDto getByOperatorName(String operatorName) {
        String[] names = operatorName.split("\\s+");

        Account account = repository.findByOperatorName(names[0], names[1]);

        return mapper.map(account, AccountDto.class);
    }

    @Override
    public List<TransactionDto> getTransactions(String nameOperator) {
        String[] names = nameOperator.split("\\s+");

        Account account = repository.findByOperatorName(names[0], names[1]);
        List<Transaction> transactions = repository.findTransactionsByAccountId(account.getId());
        return null;
    }

    @Override
    public AccountDto addTransaction(TransactionDto transactionDto, String emailOperator) {
        Account account = repository.findByOperatorEmail(emailOperator);
        transactionDto.account.setId(account.getId());
        account.setTransactions(mapper.map(transactionDto, Transaction.class));
        repository.save(account);
        return mapper.map(account, AccountDto.class);
    }

    @Override
    public AccountDto addBankStatements(BankStatementDto bankStatementDto, String operatorEmail) {
        Account account = repository.findByOperatorEmail(operatorEmail);
        bankStatementDto.account.setId(account.getId());
        account.setBankStatements(mapper.map(bankStatementDto, BankStatement.class));
        return mapper.map(account, AccountDto.class);
    }
}
