package com.example.desafiopicpay.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(){super("User with this CPF or email already exists.");}
    public UserAlreadyExistsException(String message){super(message);}
}
