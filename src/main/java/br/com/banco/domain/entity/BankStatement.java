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
public class BankStatement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false) private LocalDate created = LocalDate.now();
    @ManyToOne()
    private Account account;
    @Column(nullable = false) private LocalDate beginning;
    @Column(nullable = false) private LocalDate end;
    @Column(nullable = false) private BigDecimal totalBalancePeriod;
    @Column(nullable = false) private BigDecimal currentBalance;

    public void setTotalBalanceOfPeriod(){
        List<Transaction> transactions = account.getTransactions();
        for(Transaction t : transactions){
            if(t.getTransactionType().equals(TransactionType.DEPOSIT) || t.getTransactionType().equals(TransactionType.INCOMINGTRANSFER)) {
                this.totalBalancePeriod = this.totalBalancePeriod.add(t.getValue());
            }else {
                this.totalBalancePeriod = this.totalBalancePeriod.subtract(t.getValue());
            }
        }
    }
}
