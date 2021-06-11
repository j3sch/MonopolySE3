package com.hdm.monopoly.board.ErrorsAndExeptions;

public class WrongFieldError extends Error{

    public WrongFieldError(String errorMessage){
        super(errorMessage);
    }
}
