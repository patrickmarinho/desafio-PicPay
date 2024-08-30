package com.example.desafiopicpay.exceptions;

public class InvalidTransactionAmount extends RuntimeException{
    public InvalidTransactionAmount(){super("Transaction amount must be greater than zero.");}
    public InvalidTransactionAmount(String message){super(message);}
}
