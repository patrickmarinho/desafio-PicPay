package com.example.desafiopicpay.exceptions;

public class PayerInsufficientBalanceException extends RuntimeException{
    public PayerInsufficientBalanceException(){super("The payer's balance must be greater than zero to complete the transaction.");}
    public PayerInsufficientBalanceException(String message){super(message);}
}
