package com.example.desafiopicpay.service;

import com.example.desafiopicpay.domain.entity.transaction.Transaction;
import com.example.desafiopicpay.domain.entity.user.User;
import com.example.desafiopicpay.domain.entity.user.UserType;
import com.example.desafiopicpay.dto.EmailDTO;
import com.example.desafiopicpay.dto.TransactionDTO;
import com.example.desafiopicpay.repository.TransactionRepository;
import com.example.desafiopicpay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

        boolean isValid = validateTransaction(transactionDTO);
        if(!isValid){
            throw new RuntimeException(); // change later
        }

        User payer = userRepository.findUserById(transactionDTO.payerID());
        User payee = userRepository.findUserById(transactionDTO.payeeID());

        updateBalances(payer, payee, transactionDTO.amount());

        Transaction transaction = createTransaction(transactionDTO, payer, payee);

        transactionRepository.save(transaction);

        return transactionDTO;
    }

    private boolean validateTransaction(TransactionDTO transactionDTO){
        User payer = userRepository.findUserById(transactionDTO.payerID());

        if(payer.getCurrentBalance().compareTo(BigDecimal.ZERO) <= 0){
            return false;
        }

        if(payer.getCurrentBalance().compareTo(transactionDTO.amount()) < 0){
            return false;
        }

        if(transactionDTO.amount().compareTo(BigDecimal.ZERO) <=0){
            return false;
        }

        if(payer.getUserType() == UserType.SHOPKEEPER){
            return false;
        }

        boolean isAuthorized = authorizationService.authorizeTransaction();
        if(!isAuthorized){
            return false;
        }
        return true;
    }

    private void updateBalances(User payer, User payee, BigDecimal amount) {
        payer.setCurrentBalance(payer.getCurrentBalance().subtract(amount));
        payee.setCurrentBalance(payee.getCurrentBalance().add(amount));

        EmailDTO payeeNotification = new EmailDTO(payee.getEmail(),
                "Transfer Received Successfully",
                "You have received a transfer.");

        boolean payeeReceivedNotification = notificationService.sendNotification(payeeNotification);
        if(!payeeReceivedNotification){
            throw new RuntimeException(); // change later
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
