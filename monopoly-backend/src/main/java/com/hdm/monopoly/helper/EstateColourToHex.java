package com.hdm.monopoly.helper;


import com.hdm.monopoly.utility.EstateColour;

public class EstateColourToHex {
    public String getHexOfColour(String i){
        switch(i){
            case "Brown": return EstateColour.BROWN;
            case "LightBlue": return EstateColour.LIGHT_BLUE;
            case "Pink": return EstateColour.PINK;
            case "Orange": return EstateColour.ORANGE;
            case "Red": return EstateColour.RED;
            case "Yellow": return EstateColour.YELLOW;
            case "Green": return EstateColour.GREEN;
            case "DarkBlue": return EstateColour.DARK_BLUE;
            default: return null;
        }
    }}
