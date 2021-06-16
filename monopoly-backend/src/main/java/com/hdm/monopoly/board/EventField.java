package com.hdm.monopoly.board;

import com.hdm.monopoly.sendmessage.SendMessage;
import com.hdm.monopoly.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class EventField implements Field{
private static final Logger log = LogManager.getLogger(EventField.class);

    private final String name;

    public EventField(String name){
        this.name = name;
        log.info("New Object 'EventField' created");
    }

    @Override
    public void moveOnField(Player player, SendMessage sendMessage, String[] SessionIds) {
        //Random number 0 to 6
        Random random = new Random();
        int randomNumber = random.nextInt(7);

        // switch activate the different event field actions with a random number
        switch (randomNumber){
            //Player move to Go
            case 0:
                player.setPosition(0);
                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", "Event Field: Move to go");
                break;
                //Player move to Park Place
            case 1:
                player.setPosition(22);
                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", "Event Field: Move to  Park Place");
                break;
            //Money to Free Parking 2$
            case 2:
                player.playerPaysMoney(2);
                FreeParking.setCredit(2);
                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", "Event Field: Pay 2 $ to Free Parking");
                break;
            //Money to Free Parking 1$
            case 3:
                player.playerPaysMoney(1);
                FreeParking.setCredit(2);
                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", "Event Field: Pay 1 $ to Free Parking");
                break;
            //current player gets money
            case 4:
                player.playerGetsMoney(2);
                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", "Event Field: You get 2$ from the bank");
                break;
            //player pays 1$ to the bank
            case 5:
                player.playerPaysMoney(1);
                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", "Event Field: You pay 1$ to the bank");
                break;
            //player to jail
            case 6:
                player.setPosition(6);
                player.getArrested();
                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", "Event Field: Go to jail");
                break;
        }
    }

    @Override
    public String getFieldName() {
        return name;
    }
}
