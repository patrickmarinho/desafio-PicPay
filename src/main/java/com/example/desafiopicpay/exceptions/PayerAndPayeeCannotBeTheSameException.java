package com.example.desafiopicpay.exceptions;

public class PayerAndPayeeCannotBeTheSameException extends RuntimeException{
    public PayerAndPayeeCannotBeTheSameException(){super("The payer and payee cannot be the same person. Please specify different payer and payee.");}
    public PayerAndPayeeCannotBeTheSameException(String message){super(message);}
}
