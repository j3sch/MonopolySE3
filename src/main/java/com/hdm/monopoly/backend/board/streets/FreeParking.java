package com.hdm.monopoly.backend.board.streets;

import com.hdm.monopoly.backend.board.send_message.Notified;
import com.hdm.monopoly.backend.board.send_message.SendMessage;
import com.hdm.monopoly.backend.player_money.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FreeParking implements Field{
    private static Logger log = LogManager.getLogger(FreeParking.class);

    private String name;
    static int credit;


    public static void setCredit(int amount) {
       credit = amount;
    }

    public FreeParking(String name){
        this.name = name;
        log.debug("New Object 'FreeParking' created");
    }

    @Override
    public void moveOnField(Player player, SendMessage sendMessage, String[] SessionIds) {

        if(credit >=1 ){
            sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", "Free Parking" + credit);

            player.PlayerGetsMoney(credit);}

        else{
            sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", "Free Parking credit:" + credit);

        }

    }

    @Override
    public String getFieldName() {
        return name;
    }
}
