package br.com.banco.repository;

import br.com.banco.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t " +
            "JOIN t.account a " +
            "JOIN a.operator o " +
            "WHERE o.firstName = :firstName " +
            "AND o.lastName = :lastName " +
            "AND t.date BETWEEN :startDate AND :endDate")
    List<Transaction> findTransactionsByOperatorAndDateRange(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
}
