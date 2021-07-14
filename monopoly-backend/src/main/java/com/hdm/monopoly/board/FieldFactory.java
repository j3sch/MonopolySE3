package com.hdm.monopoly.board;

import com.hdm.monopoly.errors.WrongFieldError;

public class FieldFactory {
    protected static Field createField(String fieldType, String fieldName, int price, int rent, Colour colour, int goValue) throws WrongFieldError {
        switch (fieldType){
            case "Street":
                return new Street(fieldName,price,rent, colour);
            case "Go":
                return new Go(fieldName, goValue);
            case "Jail":
                return new Jail(fieldName);
            case "FreeParking":
                return new FreeParking(fieldName);
            case "GoToJail":
                return new GoToJail(fieldName);
            case "EventField":
                return new EventField(fieldName);
            default:
                throw new WrongFieldError("Wrong Field Type");
        }
    }
}
