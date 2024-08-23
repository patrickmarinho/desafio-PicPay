package com.example.desafiopicpay.dto;

import com.example.desafiopicpay.domain.entity.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastName, String cpfCnpj, String email,
                      String password, BigDecimal currentBalance, UserType userType) {
}
