package com.hdm.monopoly.backend.board.streets;

import com.hdm.monopoly.backend.board.send_message.Notified;
import com.hdm.monopoly.backend.board.send_message.SendMessage;
import com.hdm.monopoly.backend.player_money.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Go implements Field {
    private static Logger log = LogManager.getLogger(Go.class);

    private final String name;
    private final int goValue;

    public Go(String name,int goValue){
        this.name = name;
        this.goValue = goValue;
        log.debug("New Object 'Go' created");
    }

    @Override
    public void moveOnField(Player player, SendMessage sendMessage, String[] SessionIds, Map map) {
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
