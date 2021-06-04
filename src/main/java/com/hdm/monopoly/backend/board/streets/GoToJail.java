package com.hdm.monopoly.backend.board.streets;

import com.hdm.monopoly.backend.board.send_message.Notified;
import com.hdm.monopoly.backend.player_money.Player;
import org.springframework.beans.factory.annotation.Autowired;

public class GoToJail implements Field{

    private String name;
    private Notified notified;

    @Autowired
    public GoToJail(Notified notified) {
        this.notified = notified;
    }

    public GoToJail(String name){
        this.name = name;
    }

    @Override
    public void moveOnField(Player player) {
        notified.currentPlayer("You have to go to the jail");
        player.setPosition(6);
        player.getArrested();
    }

    @Override
    public String getFieldName() {
        return name;
    }
}
