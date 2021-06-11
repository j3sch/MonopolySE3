package com.hdm.monopoly.board.streets;

import com.hdm.monopoly.board.send_message.SendMessage;
import com.hdm.monopoly.player_money.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GoToJail implements Field{
    private static Logger log = LogManager.getLogger(GoToJail.class);

    private String name;

    public GoToJail(String name){
        this.name = name;
        log.debug("New Object 'GoToJail' created");
    }

    @Override
    public void moveOnField(Player player, SendMessage sendMessage, String[] SessionIds) {
        player.setPosition(6);
        player.getArrested();
        log.info(player.getName() + " moves into jail");
    }

    @Override
    public String getFieldName() {
        return name;
    }
}
