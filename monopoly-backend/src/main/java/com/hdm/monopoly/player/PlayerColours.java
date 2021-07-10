package com.hdm.monopoly.player;

import com.hdm.monopoly.utility.PlayerColour;

public class PlayerColours {
    public String getPlayerColour(int i){
        switch(i){
            case 0: return PlayerColour.RED;
            case 1: return PlayerColour.BLUE;
            case 2: return PlayerColour.GREEN;
            case 3: return PlayerColour.ORANGE;
            default: return null;
        }
    }
}
