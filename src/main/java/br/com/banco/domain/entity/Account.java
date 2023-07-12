package br.com.banco.domain.entity;

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
    private List<Transaction> transactionsCarriedOut;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "account")
    private List<Transaction> incomingTransactions;
    @Column(nullable = false)private LocalDate createdAt;

}
