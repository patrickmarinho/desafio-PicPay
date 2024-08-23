package com.example.desafiopicpay.domain.entity.transaction;

import com.example.desafiopicpay.domain.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity(name="transactions")
@Table(name="transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="amount")
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name="payer_id")
    private User payer;

    @ManyToOne
    @JoinColumn(name="payee_id")
    private User payee;

    @Column(name="transaction_date")
    private OffsetDateTime transactionDateTime;
}
