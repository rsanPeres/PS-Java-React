package br.com.banco.application.service.impl;

import br.com.banco.application.dto.AccountDto;
import br.com.banco.application.dto.BankStatementDto;
import br.com.banco.application.dto.TransactionDto;
import br.com.banco.application.service.IAccountService;
import br.com.banco.domain.entity.Account;
import br.com.banco.domain.entity.BankStatement;
import br.com.banco.domain.entity.Transaction;
import br.com.banco.domain.exception.NotFoundByIdException;
import br.com.banco.domain.exception.NotFoundByNameException;
import br.com.banco.domain.exception.NotFoundByOperatorEmailException;
import br.com.banco.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        if(account.getId().describeConstable().isPresent()) {
            return mapper.map(account, AccountDto.class);
        }
        else {
            throw new NotFoundByIdException("Account not found by Id");
        }
    }

    @Override
    public AccountDto getByOperatorEmail(String emailOperator) {
        Account account = repository.findByOperatorEmail(emailOperator);
        if(account.getId().describeConstable().isPresent()) {
            return mapper.map(account, AccountDto.class);
        }else {
            throw new NotFoundByOperatorEmailException("Account not found by email");
        }
    }

    @Override
    public AccountDto getByOperatorName(String operatorName) {
        String[] names = operatorName.split("\\s+");

        Account account = repository.findByOperatorName(names[0], names[1]);
        if(account.getId().describeConstable().isPresent()) {
            return mapper.map(account, AccountDto.class);
        }else {
            throw new NotFoundByNameException("Account not Found by operator name");
        }
    }

    @Override
    public List<TransactionDto> getTransactions(String nameOperator) {
        String[] names = nameOperator.split("\\s+");

        Account account = repository.findByOperatorName(names[0], names[1]);
        List<Transaction> transactions = repository.findTransactionsByAccountId(account.getId());
        if(!transactions.isEmpty()){
            return transactions.stream().map(x -> mapper.map(x, TransactionDto.class)).collect(Collectors.toList());
        }else {
            throw new NotFoundByNameException("Transactions not found by operator name");
        }
    }

    @Override
    public AccountDto addTransaction(TransactionDto transactionDto, String emailOperator) {
        Account account = repository.findByOperatorEmail(emailOperator);
        if(account.getId().describeConstable().isEmpty()){
            throw new NotFoundByOperatorEmailException("Account not found by operator email");
        }else {
            transactionDto.account.setId(account.getId());
            account.setTransactions(mapper.map(transactionDto, Transaction.class));
            repository.save(account);
            return mapper.map(account, AccountDto.class);
        }
    }

    @Override
    public AccountDto addBankStatements(BankStatementDto bankStatementDto, String operatorEmail) {
        Account account = repository.findByOperatorEmail(operatorEmail);
        if(account.getId().describeConstable().isEmpty()){
            throw new NotFoundByOperatorEmailException("Account not found by operator email");
        }else {
            bankStatementDto.account.setId(account.getId());
            account.setBankStatements(mapper.map(bankStatementDto, BankStatement.class));
            return mapper.map(account, AccountDto.class);
        }
    }
}
