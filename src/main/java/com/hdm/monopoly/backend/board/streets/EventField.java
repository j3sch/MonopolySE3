package com.hdm.monopoly.backend.board.streets;

import com.hdm.monopoly.backend.board.send_message.Notified;
import com.hdm.monopoly.backend.board.send_message.SendMessage;
import com.hdm.monopoly.backend.player_money.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class EventField implements Field{
private static Logger log = LogManager.getLogger(EventField.class);

    private final String name;

    public EventField(String name){
        this.name = name;
        log.debug("New Object 'EventField' created");
    }

    @Override
    public void moveOnField(Player player, SendMessage sendMessage, String[] SessionIds, Map map) {

        //Random number 0 to 6
        Random random = new Random();
        int randomNumber = random.nextInt(7);
        FreeParking freeParking = (FreeParking) map.getField(22);

        // switch aktivate the different event field actions with a random number
        switch (randomNumber){

            //Player move to Go
            case 0:

                map.getField(0).moveOnField(player, sendMessage, SessionIds, map);
                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", "Event Field: Move to go");
                break;
            //Player move to Park Place
            case 1:
                map.getField(22).moveOnField(player, sendMessage, SessionIds, map);
                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", "Event Field: Move to  Park Place");


                break;

            //Money to Free Parking 2$
            case 2:
                player.PlayerPaysMoney(2);
                freeParking.setCredit(2);
                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", "Event Field: Pay 2 $ to Free Parking");
                break;

            //Money to Free Parking 1$
            case 3:
                player.PlayerPaysMoney(2);
                freeParking.setCredit(2);
                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", "Event Field: Pay 1 $ to Free Parking");
                break;

            //current player gets money
            case 4:
                player.PlayerGetsMoney(2);
                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", "Event Field: You get 2$ from the bank");
                break;

            //player pays 1$ to the bank
            case 5:
                player.PlayerPaysMoney(1);
                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", "Event Field: You pay 1$ to the bank");
                break;

            //player to jail
            case 6:
                map.getField(18).moveOnField(player, sendMessage, SessionIds, map);
                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", "Event Field: Go to jail");
                break;
        }




    }

    @Override
    public String getFieldName() {
        return name;
    }
}
