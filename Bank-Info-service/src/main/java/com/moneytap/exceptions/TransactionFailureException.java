package com.moneytap.exceptions;

public class TransactionFailureException extends Exception{
    public TransactionFailureException(String msg){
        super(msg);
    }
}
