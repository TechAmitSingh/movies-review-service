package com.verizon.in.exception;

public class ReviewDataException extends RuntimeException{

    private String message;

    public ReviewDataException(String mes){
        super(mes);
        this.message=mes;
    }
}
