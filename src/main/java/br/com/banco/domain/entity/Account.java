package br.com.banco.domain.entity;

import br.com.banco.domain.enummeration.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @OneToOne
    private Operator operator;
    private BigDecimal accountBalance;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private List<BankStatement> bankStatements;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "account")
    private List<Transaction> transactions;
    @Column(nullable = false)private LocalDate createdAt = LocalDate.now();

    public void setBankStatements(BankStatement bankStatement){
        this.bankStatements.add(bankStatement);
    }

    public void setTransactions(Transaction transaction){
        this.transactions.add(transaction);
        setAccountBalance(transaction.getValue(), transaction.getTransactionType());
    }

    public void setAccountBalance(BigDecimal value, TransactionType transactionType) {
        if(transactionType.equals(TransactionType.DEPOSIT) || transactionType.equals(TransactionType.INCOMINGTRANSFER)) {
            this.accountBalance = this.accountBalance.add(value);
        }else{
            this.accountBalance = this.accountBalance.subtract(value);
        }
    }
}
