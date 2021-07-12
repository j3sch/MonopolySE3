package com.hdm.monopoly.board;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hdm.monopoly.sendmessage.SendMessage;
import com.hdm.monopoly.player.Player;
import com.hdm.monopoly.sendmessage.SendPlayerData;
import com.hdm.monopoly.utility.FieldPosition;
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
    public void moveOnField(Player player, SendMessage sendMessage, String[] SessionIds, Board board, SendPlayerData sendPlayerData) {

        int randomNumber = (int) (Math.random() * 7);
        FreeParking freeParking = (FreeParking) board.getField(FieldPosition.FREE_PARKING);

        // switch activate the different event field actions with a random number
        switch (randomNumber){

            //Player move to Go
            case 0:
                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/eventFieldMessage", "Event Field: Move to go");
                new Thread(() -> {
                    try {
                        Thread.sleep(2000);
                        player.setPosition(FieldPosition.GO_FIELD);
                        sendPlayerData.sendPlayerToClient();
                        board.getField(FieldPosition.GO_FIELD).moveOnField(player, sendMessage, SessionIds, board, sendPlayerData);
                    } catch (InterruptedException | JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }).start();
                break;

            //Player move to Park Place
            case 1:
                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/eventFieldMessage", "Event Field: Move to Park Place");
                new Thread(() -> {
                    try {
                        Thread.sleep(2000);
                        player.setPosition(FieldPosition.PARK_PALACE_FIELD);
                        sendPlayerData.sendPlayerToClient();
                        board.getField(FieldPosition.PARK_PALACE_FIELD).moveOnField(player, sendMessage, SessionIds, board, sendPlayerData);
                    } catch (InterruptedException | JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }).start();
                break;

            //Money to Free Parking 2$
            case 2:
                player.playerPaysMoney(2);
                freeParking.setCredit(2);
                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/eventFieldMessage", "Event Field: Pay 2$ to Free Parking");
                break;

            //Money to Free Parking 1$
            case 3:
                player.playerPaysMoney(1);
                freeParking.setCredit(1);
                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/eventFieldMessage", "Event Field: Pay 1$ to Free Parking");
                break;

            //current player gets money
            case 4:
                player.playerGetsMoney(2);
                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/eventFieldMessage", "Event Field: You get 2$ from the bank");
                break;

            //player pays 1$ to the bank
            case 5:
                player.playerPaysMoney(1);
                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/eventFieldMessage", "Event Field: You pay 1$ to the bank");
                break;

            //player to jail
            case 6:
                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/eventFieldMessage", "Event Field: Go to jail");
                new Thread(() -> {
                    try {
                        Thread.sleep(2000);
                        player.setPosition(FieldPosition.JAIL_FIELD);
                        sendPlayerData.sendPlayerToClient();
                        board.getField(FieldPosition.JAIL_FIELD).moveOnField(player, sendMessage, SessionIds, board, sendPlayerData);
                    } catch (InterruptedException | JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }).start();
                break;
        }
    }

    @Override
    public String getFieldName() {
        return name;
    }
}