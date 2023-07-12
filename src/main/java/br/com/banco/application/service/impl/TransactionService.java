package br.com.banco.application.service.impl;

import br.com.banco.application.dto.TransactionDto;
import br.com.banco.application.dto.UserDto;
import br.com.banco.application.service.ITransactionService;
import br.com.banco.domain.entity.Transaction;
import br.com.banco.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionService implements ITransactionService {
    private final TransactionRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public TransactionService(TransactionRepository repository, ModelMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TransactionDto save(TransactionDto dto) {
        repository.save(mapper.map(dto, Transaction.class));
        return dto;
    }

    @Override
    public List<TransactionDto> getTransactionsByOperatorAndDateRange(LocalDate start, LocalDate end, UserDto operator) {
        List<Transaction> transactions = repository.findTransactionsByOperatorAndDateRange(operator.firstName, operator.lastName, start, end);
        return transactions.stream().map(x -> mapper.map(x, TransactionDto.class)).collect(Collectors.toList());
    }
}