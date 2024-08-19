package com.example.desafiopicpay.domain.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.NotFound;

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
    private String firstName;

    @NotNull @NotBlank
    private String lastName;

    @Column(unique=true)
    @NotNull @NotBlank
    private String cpfCnpj;

    @Column(unique=true)
    @NotNull @NotBlank
    private String email;

    @NotNull
    private String password;

    @NotNull
    private BigDecimal currentBalance;

    @Enumerated(EnumType.STRING)
    private UserType userType;
}
