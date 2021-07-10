package com.hdm.monopoly.player;

import com.hdm.monopoly.utility.Constants;

public class PlayerColours {
    public String getPlayerColour(int i){
        switch(i){
            case 0: return Constants.PLAYER_COLOUR_RED;
            case 1: return Constants.PLAYER_COLOUR_BLUE;
            case 2: return Constants.PLAYER_COLOUR_GREEN;
            case 3: return Constants.PLAYER_COLOUR_ORANGE;
            default: return null;
        }
    }
}
