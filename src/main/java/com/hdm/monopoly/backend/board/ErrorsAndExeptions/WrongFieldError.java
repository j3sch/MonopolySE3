package com.hdm.monopoly.backend.board.ErrorsAndExeptions;

public class WrongFieldError extends Error{

    public WrongFieldError(String errorMessage){
        super(errorMessage);
    }
}
