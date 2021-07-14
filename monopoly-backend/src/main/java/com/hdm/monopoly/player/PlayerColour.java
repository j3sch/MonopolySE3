package com.hdm.monopoly.player;

public class PlayerColour {
    public String getPlayerColour(int i){
        switch(i){
            case 0: return com.hdm.monopoly.utility.PlayerColour.RED;
            case 1: return com.hdm.monopoly.utility.PlayerColour.BLUE;
            case 2: return com.hdm.monopoly.utility.PlayerColour.GREEN;
            case 3: return com.hdm.monopoly.utility.PlayerColour.ORANGE;
            default: return null;
        }
    }
}
