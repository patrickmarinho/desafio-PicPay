package com.example.desafiopicpay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNegativeBalanceException.class)
    private ResponseEntity<String> handleUserNegativeBalanceException(UserNegativeBalanceException exception){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("User cannot have a negative balance.");
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    private ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException exception){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("User with this CPF or email already exists.");
    }

    @ExceptionHandler(PayerInsufficientBalanceException.class)
    private ResponseEntity<String> handlePayerInsufficientBalanceException(PayerInsufficientBalanceException exception){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Payer's balance must be equal to or greater than the amount to be transferred.");
    }

    @ExceptionHandler(InvalidTransactionAmount.class)
    private ResponseEntity<String> handleInvalidTransactionAmount(InvalidTransactionAmount exception){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Transaction amount must be greater than zero.");
    }

    @ExceptionHandler(ShopkeeperInvalidTransactionException.class)
    private ResponseEntity<String> handleShopkeeperInvalidTransactionException(ShopkeeperInvalidTransactionException exception){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Shopkeeper users are not allowed to send transactions. They can only receive them.");
    }

    @ExceptionHandler(TransactionNotAuthorizedException.class)
    private ResponseEntity<String> handleTransactionNotAuthorizedException(TransactionNotAuthorizedException exception){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Transaction not authorized. The amount has been returned to the payer's account.");
    }

    @ExceptionHandler(UnavailableServiceException.class)
    private ResponseEntity<String> handlePayeeNotNotifiedException(UnavailableServiceException exception){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Service is unavailable. The transaction has been canceled. Please try again later.");
    }

    @ExceptionHandler(PayerAndPayeeCannotBeTheSameException.class)
    private ResponseEntity<String> handlePayerAndPayeeCannotBeTheSameException(PayerAndPayeeCannotBeTheSameException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The payer and payee cannot be the same person. Please specify different payer and payee.");
    }
}
