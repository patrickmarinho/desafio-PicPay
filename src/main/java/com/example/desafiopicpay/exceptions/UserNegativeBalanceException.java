package com.example.desafiopicpay.exceptions;

public class UserNegativeBalanceException extends RuntimeException{
    public UserNegativeBalanceException(){super("User cannot have a negative balance.");}
    public UserNegativeBalanceException(String message){super(message);}
}
