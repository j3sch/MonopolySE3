package com.hdm.monopoly.board;

import com.hdm.monopoly.sendmessage.SendMessage;
import com.hdm.monopoly.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Go implements Field {
    private static final Logger log = LogManager.getLogger(Go.class);

    private final String name;
    private final int goValue;

    public Go(String name,int goValue){
        this.name = name;
        this.goValue = goValue;
        log.info("New Object 'Go' created");
    }

    @Override
    public void moveOnField(Player player, SendMessage sendMessage, String[] SessionIds, Board board) {
        player.playerGetsMoney(goValue);
        sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", " You get $" + goValue);
    }

    public int getGoValue(){
        return goValue;
    }

    @Override
    public String getFieldName() {
        return name;
    }

}
