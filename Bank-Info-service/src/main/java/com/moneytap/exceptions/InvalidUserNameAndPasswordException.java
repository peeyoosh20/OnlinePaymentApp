package com.moneytap.exceptions;

public class InvalidUserNameAndPasswordException extends Exception{
    public InvalidUserNameAndPasswordException(String msg){
        super(msg);
    }
}
