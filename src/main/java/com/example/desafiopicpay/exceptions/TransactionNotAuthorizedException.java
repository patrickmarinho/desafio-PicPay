package com.example.desafiopicpay.exceptions;

public class TransactionNotAuthorizedException extends RuntimeException{
    public TransactionNotAuthorizedException(){super("Transaction not authorized. The amount has been returned to the payer's account.");}
}
