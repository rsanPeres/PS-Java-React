package br.com.banco.application.service.impl;

import br.com.banco.application.dto.BankStatementDto;
import br.com.banco.application.dto.TransactionDto;
import br.com.banco.application.service.IAccountService;
import br.com.banco.application.service.IBankStatementService;
import br.com.banco.domain.entity.BankStatement;
import br.com.banco.repository.BankStatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankStatementService implements IBankStatementService {
    private final BankStatementRepository repository;
    private final IAccountService accountService;

    @Autowired
    public BankStatementService(BankStatementRepository repository,
                                IAccountService accountService){
        this.repository = repository;
        this.accountService = accountService;
    }


    @Override
    public List<BankStatementDto> getAll(String nameOperator) {
        String[] names = nameOperator.split("\\s+");

        List<BankStatement> statements = repository.findBankStatementsByOperatorName(names[0], names[1]);
        return mapStatement(statements);
    }

    @Override
    public List<BankStatementDto> getByCreationDate(LocalDate criation) {
        List<BankStatement> statements = repository.findByCreationDate(criation);
        return mapStatement(statements);
    }

    @Override
    public BankStatementDto create(LocalDate biginning, LocalDate end, String nameOperator) {
        List<TransactionDto> transactionDto = accountService.getTransactions(nameOperator);
        BankStatement statement = new BankStatement();
        statement.setAccount(transactionDto.get(0).account);
        statement.setBeginning(biginning);
        statement.setEnd(end);
        statement.setCurrentBalance(statement.getAccount().getAccountBalance());
        statement.setTotalBalanceOfPeriod();
        repository.save(statement);
        return new BankStatementDto(statement);
    }

    private List<BankStatementDto> mapStatement(List<BankStatement> statements){
        return statements.stream()
                .map(BankStatementDto::new)
                .collect(Collectors.toList());
    }
}
