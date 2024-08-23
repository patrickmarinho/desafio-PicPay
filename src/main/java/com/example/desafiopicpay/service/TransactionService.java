package com.example.desafiopicpay.service;

import com.example.desafiopicpay.domain.entity.transaction.Transaction;
import com.example.desafiopicpay.domain.entity.user.User;
import com.example.desafiopicpay.domain.entity.user.UserType;
import com.example.desafiopicpay.dto.TransactionDTO;
import com.example.desafiopicpay.repository.TransactionRepository;
import com.example.desafiopicpay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;



    //Post
    public TransactionDTO executeTransaction(TransactionDTO transactionDTO){
        validateTransaction(transactionDTO);
        User payer = userRepository.findUserById(transactionDTO.payerID());
        User payee = userRepository.findUserById(transactionDTO.payeeID());

        boolean isAuthorized = authorizeTransaction();
        if(!isAuthorized){
            throw new RuntimeException("erro NÃO AUTORIZADO");
        }

        updateBalances(payer, payee, transactionDTO.amount());

        Transaction transaction = createTransaction(transactionDTO, payer, payee);


        transactionRepository.save(transaction);
        return transactionDTO;
    }

    private boolean authorizeTransaction(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://util.devi.tools/api/v2/authorize";

        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity(url, Map.class);

        if(authorizationResponse.getStatusCode() == HttpStatus.OK){
            return true;
        }else return false;
    }

    private void updateBalances(User payer, User payee, BigDecimal amount) {
        payer.setCurrentBalance(payer.getCurrentBalance().subtract(amount));
        payee.setCurrentBalance(payee.getCurrentBalance().add(amount));

        userRepository.save(payer);
        userRepository.save(payee);
    }

    private Transaction createTransaction(TransactionDTO transactionDTO, User payer, User payee){
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.amount());
        transaction.setPayer(payer);
        transaction.setPayee(payee);
        transaction.setTransactionDateTime(OffsetDateTime.now());
        return transaction;
    }

    private void validateTransaction(TransactionDTO transactionDTO){
        User payer = userRepository.findUserById(transactionDTO.payerID());

        if(payer.getCurrentBalance().compareTo(BigDecimal.ZERO) <= 0){
            throw new RuntimeException(); // change later
        }

        if(payer.getCurrentBalance().compareTo(transactionDTO.amount()) < 0){
            throw new RuntimeException(); // change later
        }

        if(payer.getUserType() == UserType.SHOPKEEPER){
            throw new RuntimeException(); // change later
        }
    }
}
