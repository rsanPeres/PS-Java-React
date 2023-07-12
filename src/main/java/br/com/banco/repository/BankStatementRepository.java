package br.com.banco.repository;

import br.com.banco.domain.entity.BankStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BankStatementRepository extends JpaRepository<BankStatement, Long> {
    @Query("SELECT bs FROM BankStatement bs " +
            "JOIN bs.account a " +
            "JOIN a.operator o " +
            "WHERE o.firstName = :firstName AND o.lastName = :lastName")
    List<BankStatement> findBankStatementsByOperatorName(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName);

    @Query("SELECT bs FROM BankStatement bs WHERE bs.created = :created")
    List<BankStatement> findByCreationDate(@Param("created") LocalDate created);

}
