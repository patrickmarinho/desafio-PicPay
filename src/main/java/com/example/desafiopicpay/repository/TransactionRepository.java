package com.example.desafiopicpay.repository;

import com.example.desafiopicpay.domain.entity.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
