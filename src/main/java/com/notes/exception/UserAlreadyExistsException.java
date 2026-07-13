package com.notes.exception;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String msg){
        super(msg);
    }
}
