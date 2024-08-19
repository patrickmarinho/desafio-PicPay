package com.example.desafiopicpay.domain.entity.transaction;

import com.example.desafiopicpay.domain.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

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
    private Long id;

    private BigDecimal amount;

    @ManyToOne
    private User payer;

    @ManyToOne
    private User payee;
}
