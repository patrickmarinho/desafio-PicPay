package com.example.desafiopicpay.domain.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @NotBlank
    @Column(name="first_name")
    private String firstName;

    @NotNull @NotBlank
    @Column(name="last_name")
    private String lastName;

    @NotNull @NotBlank
    @Column(name="cpf_cnpj", unique=true)
    private String cpfCnpj;

    @NotNull @NotBlank
    @Column(name="email", unique=true)
    private String email;

    @NotNull @NotBlank
    @Column(name="password")
    private String password;

    @NotNull
    @Column(name="current_balance")
    private BigDecimal currentBalance;

    @Column(name="user_type")
    @NotNull
    @Enumerated(EnumType.STRING)
    private UserType userType;
}
