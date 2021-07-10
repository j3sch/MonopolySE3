package com.hdm.monopoly.board;

import com.hdm.monopoly.errors.WrongFieldError;

public class FieldFactory {
    protected static Field createField(String type, String name, int price, int rent, Colour colour, int goValue) throws WrongFieldError {
        switch (type){
            case "Street":
                return new Street(name,price,rent, colour);
            case "Go":
                return new Go(name, goValue);
            case "Jail":
                return new Jail(name);
            case "FreeParking":
                return new FreeParking(name);
            case "GoToJail":
                return new GoToJail(name);
            case "EventField":
                return new EventField(name);
            default:
                throw new WrongFieldError("Wrong Field Type");
        }
    }
}
