package com.hdm.monopoly.helper;

import com.hdm.monopoly.utility.Constants;

public class EstateColourToHex {
    public String getHexOfColour(String i){
        switch(i){
            case "Brown": return Constants.ESTATE_COLOUR_BROWN;
            case "LightBlue": return Constants.ESTATE_COLOUR_LIGHT_BLUE;
            case "Pink": return Constants.ESTATE_COLOUR_PINK;
            case "Orange": return Constants.ESTATE_COLOUR_ORANGE;
            case "Red": return Constants.ESTATE_COLOUR_RED;
            case "Yellow": return Constants.ESTATE_COLOUR_YELLOW;
            case "Green": return Constants.ESTATE_COLOUR_GREEN;
            case "DarkBlue": return Constants.ESTATE_COLOUR_DARK_BLUE;
            default: return null;
        }
    }}
