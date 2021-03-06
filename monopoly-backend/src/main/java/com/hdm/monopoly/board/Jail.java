package com.hdm.monopoly.board;

import com.hdm.monopoly.sendmessage.SendMessage;
import com.hdm.monopoly.player.Player;
import com.hdm.monopoly.sendmessage.SendPlayerData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * implemented field type that represents the field which tells you that you are now visiting the prison
 */
public class Jail implements Field{
    private static final Logger log = LogManager.getLogger(Jail.class);

    private final String name;

    public Jail(String name){
        this.name = name;
        log.info("New Object 'Jail' created");
    }

    @Override
    public void moveOnField(Player player, SendMessage sendMessage, String[] SessionIds, Board board, SendPlayerData sendPlayerData) {
        sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", "You visit the prison");
    }

    @Override
    public String getFieldName() {
        return name;
    }
}
