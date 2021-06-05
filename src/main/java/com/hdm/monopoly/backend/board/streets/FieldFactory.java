package com.hdm.monopoly.backend.board.streets;

import com.hdm.monopoly.backend.board.ErrorsAndExeptions.WrongFieldError;
import com.hdm.monopoly.backend.board.send_message.SendMessage;

public class FieldFactory {

    public static Field createField(String type, String name, int price, int rent, Color color, int goValue, SendMessage sendmessage, String[] SessionIds) throws WrongFieldError {
        switch (type){
            case "Street":
                return new Street(name,price,rent,color, sendmessage, SessionIds);
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
