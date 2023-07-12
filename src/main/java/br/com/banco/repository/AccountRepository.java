package br.com.banco.repository;

import br.com.banco.domain.entity.Account;
import br.com.banco.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT a FROM Account a JOIN a.operator o WHERE o.email = :email")
    Account findByOperatorEmail(@Param("email") String email);

    @Query("SELECT a FROM Account a JOIN a.operator o WHERE o.firstName = :firstName AND o.lastName = :lastName")
    Account findByOperatorName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query("SELECT t FROM Transaction t JOIN t.account a WHERE a.id = :accountId")
    List<Transaction> findTransactionsByAccountId(@Param("accountId") Long accountId);

}
