package com.example.desafiopicpay.exceptions;

public class ShopkeeperInvalidTransactionException extends RuntimeException{
    public ShopkeeperInvalidTransactionException(){super("Shopkeeper users are not allowed to send transactions. They can only receive them.");}
    public ShopkeeperInvalidTransactionException(String message){super(message);}
}
