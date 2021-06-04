package com.hdm.monopoly.backend.board.streets;

import com.hdm.monopoly.backend.board.send_message.SendPlayerData;
import com.hdm.monopoly.backend.di.GameConfig;
import com.hdm.monopoly.backend.player_money.Player;
import org.springframework.beans.factory.annotation.Autowired;

public class GoToJail implements Field{

    private String name;

    public GoToJail(String name){
        this.name = name;
    }

    @Override
    public void moveOnField(Player player) {

        player.setPosition(6);
        player.getArrested();
    }

    @Override
    public String getFieldName() {
        return name;
    }
}
