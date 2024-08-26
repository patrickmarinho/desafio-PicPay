package com.example.desafiopicpay.dto;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal amount, Long payerID, Long payeeID) {
}
