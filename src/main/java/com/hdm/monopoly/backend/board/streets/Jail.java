package com.hdm.monopoly.backend.board.streets;

import com.hdm.monopoly.backend.board.send_message.Notified;
import com.hdm.monopoly.backend.board.send_message.SendMessage;
import com.hdm.monopoly.backend.player_money.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Jail implements Field{
    private static Logger log = LogManager.getLogger(Jail.class);

    private String name;

    public Jail(String name){
        this.name = name;
        log.debug("New Object 'Jail' created");
    }

    @Override
    public void moveOnField(Player player, SendMessage sendMessage, String[] SessionIds) {
        //TODO
    }

    @Override
    public String getFieldName() {
        return name;
    }
}
