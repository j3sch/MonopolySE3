package com.hdm.monopoly.backend.board.streets;

import com.hdm.monopoly.backend.board.send_message.SendMessage;
import com.hdm.monopoly.backend.player_money.Player;

public class GoToJail implements Field{

    private String name;

    public GoToJail(String name){
        this.name = name;
    }

    @Override
    public void moveOnField(Player player, SendMessage sendMessage, String[] SessionIds) {
        player.setPosition(6);
        player.getArrested();
    }

    @Override
    public String getFieldName() {
        return name;
    }
}
