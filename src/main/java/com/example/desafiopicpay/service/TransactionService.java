package com.example.desafiopicpay.service;

import com.example.desafiopicpay.domain.entity.transaction.Transaction;
import com.example.desafiopicpay.domain.entity.user.User;
import com.example.desafiopicpay.domain.entity.user.UserType;
import com.example.desafiopicpay.dto.EmailDTO;
import com.example.desafiopicpay.dto.TransactionDTO;
import com.example.desafiopicpay.exceptions.*;
import com.example.desafiopicpay.repository.TransactionRepository;
import com.example.desafiopicpay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;


@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthorizationService authorizationService;

    @Autowired
    NotificationService notificationService;

    //Post
    public TransactionDTO executeTransaction(TransactionDTO transactionDTO){

        validateTransaction(transactionDTO);

        User payer = userRepository.findUserById(transactionDTO.payerID());
        User payee = userRepository.findUserById(transactionDTO.payeeID());

        updateBalances(payer, payee, transactionDTO.amount());

        Transaction transaction = createTransaction(transactionDTO, payer, payee);

        transactionRepository.save(transaction);

        return transactionDTO;
    }

    private void validateTransaction(TransactionDTO transactionDTO){
        User payer = userRepository.findUserById(transactionDTO.payerID());

        if(payer.getCurrentBalance().compareTo(BigDecimal.ZERO) <= 0){
            throw new PayerInsufficientBalanceException();
        }

        if(transactionDTO.payerID() == transactionDTO.payeeID()){
            throw new PayerAndPayeeCannotBeTheSameException();
        }

        if(payer.getCurrentBalance().compareTo(transactionDTO.amount()) < 0){
            throw new PayerInsufficientBalanceException();
        }

        if(transactionDTO.amount().compareTo(BigDecimal.ZERO) <=0){
            throw new InvalidTransactionAmount();
        }

        if(payer.getUserType() == UserType.SHOPKEEPER){
            throw new ShopkeeperInvalidTransactionException();
        }

        boolean isAuthorized = authorizationService.authorizeTransaction();
        if(!isAuthorized){
            throw new TransactionNotAuthorizedException();
        }

    }

    private void updateBalances(User payer, User payee, BigDecimal amount) {
        payer.setCurrentBalance(payer.getCurrentBalance().subtract(amount));
        payee.setCurrentBalance(payee.getCurrentBalance().add(amount));

        EmailDTO payeeNotification = new EmailDTO(payee.getEmail(),
                "Transfer Received Successfully",
                "You have received a transfer.");

        boolean payeeReceivedNotification = notificationService.sendNotification(payeeNotification);
        if(!payeeReceivedNotification){
            throw new UnavailableServiceException(); // change later
        }

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
}
