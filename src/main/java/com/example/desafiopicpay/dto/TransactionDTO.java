package com.example.desafiopicpay.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransactionDTO(BigDecimal amount, Long payerID, Long payeeID) {
}
