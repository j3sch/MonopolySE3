package com.hdm.monopoly.board;

import com.hdm.monopoly.sendmessage.SendMessage;
import com.hdm.monopoly.player.Player;
import com.hdm.monopoly.sendmessage.SendPlayerData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * implemented field type that represents the start of the map
 */
public class Go implements Field {
    private static final Logger log = LogManager.getLogger(Go.class);

    private final String fieldName;
    private final int goValue;

    public Go(String fieldName,int goValue){
        this.fieldName = fieldName;
        this.goValue = goValue;
        log.info("New Object 'Go' created");
    }

    @Override
    public void moveOnField(Player player, SendMessage sendMessage, String[] SessionIds, Board board, SendPlayerData sendPlayerData) {
        player.playerGetsMoney(goValue);
        sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", " You get" + goValue + "$");
    }

    public int getGoValue(){
        return goValue;
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

}
