package com.example.desafiopicpay.controller;

import com.example.desafiopicpay.dto.TransactionDTO;
import com.example.desafiopicpay.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping()
    public TransactionDTO executeNewTransaction(@RequestBody TransactionDTO transactionDTO){
        return transactionService.executeTransaction(transactionDTO);
    };
}
