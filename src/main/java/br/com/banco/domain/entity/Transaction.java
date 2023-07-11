package br.com.banco.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import br.com.banco.domain.enummeration.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne()
    private Customer customer;
    @Column(nullable = false) private LocalDate date;
    @Column(nullable = false) private TransactionType type;
    @ManyToOne()
    private Operator operator;
    @Column(nullable = false)@Positive private BigDecimal value;
    @ManyToOne private Account account;

}
