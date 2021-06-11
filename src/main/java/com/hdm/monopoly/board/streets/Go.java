package com.hdm.monopoly.board.streets;

import com.hdm.monopoly.board.send_message.SendMessage;
import com.hdm.monopoly.player_money.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Go implements Field {
    private static Logger log = LogManager.getLogger(Go.class);

    private String name;
    private int goValue;

    public Go(String name,int goValue){
        this.name = name;
        this.goValue = goValue;
        log.debug("New Object 'Go' created");
    }

    @Override
    public void moveOnField(Player player, SendMessage sendMessage, String[] SessionIds) {
        player.PlayerGetsMoney(goValue);
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
