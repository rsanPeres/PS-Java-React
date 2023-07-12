package br.com.banco.application.service;

import br.com.banco.application.dto.TransactionDto;
import br.com.banco.application.dto.UserDto;

import java.time.LocalDate;
import java.util.List;

public interface ITransactionService {
    TransactionDto save(TransactionDto dto);
    List<TransactionDto> getTransactionsByOperatorAndDateRange(LocalDate start, LocalDate end, UserDto operator);
}
