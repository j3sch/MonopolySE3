package com.hdm.monopoly.backend.board.streets;

import com.hdm.monopoly.backend.player_money.Player;

public class FreeParking implements Field{

    private String name;

    public FreeParking(String name){
        this.name = name;
    }

    @Override
    public void moveOnField(Player player) {
        //TODO
    }

    @Override
    public String getFieldName() {
        return name;
    }
}
