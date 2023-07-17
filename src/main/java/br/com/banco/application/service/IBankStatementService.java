package br.com.banco.application.service;

import br.com.banco.application.dto.BankStatementDto;

import java.time.LocalDate;
import java.util.List;

public interface IBankStatementService {
    List<BankStatementDto> getAll(String nameOperator);
    List<BankStatementDto> getByCreationDate(LocalDate criation);
    BankStatementDto save(LocalDate biginning, LocalDate end, String nameOperator);
}
