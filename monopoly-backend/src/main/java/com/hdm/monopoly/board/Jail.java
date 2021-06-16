package com.hdm.monopoly.board;

import com.hdm.monopoly.sendmessage.SendMessage;
import com.hdm.monopoly.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Jail implements Field{
    private static final Logger log = LogManager.getLogger(Jail.class);

    private final String name;

    public Jail(String name){
        this.name = name;
        log.info("New Object 'Jail' created");
    }

    @Override
    public void moveOnField(Player player, SendMessage sendMessage, String[] SessionIds, Board board) {
        //TODO
    }

    @Override
    public String getFieldName() {
        return name;
    }
}
