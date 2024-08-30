package com.example.desafiopicpay.exceptions;

public class UnavailableServiceException extends RuntimeException{
    public UnavailableServiceException(){super("Service is unavailable. The transaction has been canceled. Please try again later.");}
    public UnavailableServiceException(String message){super(message);}
}
