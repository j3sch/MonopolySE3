package com.hdm.monopoly.errors;

public class WrongFieldError extends Error {

    public WrongFieldError(String errorMessage){
        super(errorMessage);
    }
}
