package com.hdm.monopoly.board;

import com.hdm.monopoly.sendmessage.SendMessage;
import com.hdm.monopoly.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EventField implements Field{
private static final Logger log = LogManager.getLogger(EventField.class);

    private final String name;

    public EventField(String name){
        this.name = name;
        log.info("New Object 'EventField' created");
    }

    @Override
    public void moveOnField(Player player, SendMessage sendMessage, String[] SessionIds, Board board) {

        int randomNumber = (int) (Math.random() * 7);
        FreeParking freeParking = (FreeParking) board.getField(12);

        // switch activate the different event field actions with a random number
        switch (randomNumber){

            //Player move to Go
            case 0:
                board.getField(0).moveOnField(player, sendMessage, SessionIds, board);
                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", "Event Field: Move to go");
                break;

            //Player move to Park Place
            case 1:
                board.getField(22).moveOnField(player, sendMessage, SessionIds, board);
                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", "Event Field: Move to  Park Place");
                try {
                    Thread.sleep(2000);
                    player.setPosition(22);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;

            //Money to Free Parking 2$
            case 2:
                player.playerPaysMoney(2);
                freeParking.setCredit(2);
                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", "Event Field: Pay 2 $ to Free Parking");
                break;

            //Money to Free Parking 1$
            case 3:
                player.playerPaysMoney(1);
                freeParking.setCredit(1);
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
                board.getField(18).moveOnField(player, sendMessage, SessionIds, board);
                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", "Event Field: Go to jail");
                break;
        }
    }

    @Override
    public String getFieldName() {
        return name;
    }
}
