package com.hdm.monopoly.board;

import com.hdm.monopoly.sendmessage.SendMessage;
import com.hdm.monopoly.player.Player;
import com.hdm.monopoly.sendmessage.SendPlayerData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GoToJail implements Field{
    private static final Logger log = LogManager.getLogger(GoToJail.class);

    private final String name;

    public GoToJail(String name){
        this.name = name;
        log.info("New Object 'GoToJail' created");
    }

    @Override
    public void moveOnField(Player player, SendMessage sendMessage, String[] SessionIds, Board board, SendPlayerData sendPlayerData) {
        player.setPosition(6);
        player.getArrested();
        sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", "You have to go to jail.");
        log.info(player.getName() + " moves into jail");
    }

    @Override
    public String getFieldName() {
        return name;
    }
}
