package br.com.banco.application.service.impl;

import br.com.banco.application.dto.BankStatementDto;
import br.com.banco.application.dto.TransactionDto;
import br.com.banco.application.service.IAccountService;
import br.com.banco.application.service.IBankStatementService;
import br.com.banco.domain.entity.BankStatement;
import br.com.banco.domain.exception.DateException;
import br.com.banco.domain.exception.NotFoundByCreationDateException;
import br.com.banco.domain.exception.NotFoundByNameException;
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
        if(!statements.isEmpty()){
            return mapStatement(statements);
        }else {
            throw new NotFoundByNameException("Statements not found by operator name");
        }
    }

    @Override
    public List<BankStatementDto> getByCreationDate(LocalDate criation) {
        List<BankStatement> statements = repository.findByCreationDate(criation);
        if(!statements.isEmpty()){
            return mapStatement(statements);
        }else {
            throw new NotFoundByCreationDateException("Statements not found by criation date");
        }
    }

    @Override
    public BankStatementDto save(LocalDate biginning, LocalDate end, String nameOperator) {
        List<TransactionDto> transactionDto = accountService.getTransactions(nameOperator);
        if(!transactionDto.isEmpty()) {
            BankStatement statement = new BankStatement();
            statement.setAccount(transactionDto.get(0).account);
            if(statement.getAccount().getCreatedAt().isAfter(biginning)){
                throw new DateException("Biginning is before creation date account");
            }else if(end.isAfter(LocalDate.now())){
                throw new DateException("End is after today");
            }else {
                statement.setBeginning(biginning);
                statement.setEnd(end);
                statement.setCurrentBalance(statement.getAccount().getAccountBalance());
                statement.setTotalBalanceOfPeriod();
                repository.save(statement);
                return new BankStatementDto(statement);
            }
        }else {
            throw new NotFoundByNameException("Transaction not found by operator name");
        }
    }

    private List<BankStatementDto> mapStatement(List<BankStatement> statements){
        return statements.stream()
                .map(BankStatementDto::new)
                .collect(Collectors.toList());
    }
}
