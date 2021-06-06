package com.hdm.monopoly.backend.board.streets;

import com.hdm.monopoly.backend.board.send_message.SendMessage;
import com.hdm.monopoly.backend.player_money.Player;

public class Go implements Field {

    private String name;
    private int goValue;

    public Go(String name,int goValue){
        this.name = name;
        this.goValue = goValue;
    }

    @Override
    public void moveOnField(Player player, SendMessage sendMessage, String[] SessionIds) {
        player.PlayerGetsMoney(goValue);
    }

    public int getGoValue(){
        return goValue;
    }

    @Override
    public String getFieldName() {
        return name;
    }

}
