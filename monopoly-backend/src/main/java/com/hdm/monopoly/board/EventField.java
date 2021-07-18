package com.hdm.monopoly.board;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hdm.monopoly.sendmessage.SendMessage;
import com.hdm.monopoly.player.Player;
import com.hdm.monopoly.sendmessage.SendPlayerData;
import com.hdm.monopoly.utility.ConstantInteger;
import com.hdm.monopoly.utility.FieldPosition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * implemented field type that represents the event fields
 */
public class EventField implements Field{
    private static final Logger log = LogManager.getLogger(EventField.class);

    private final String fieldName;

    public EventField(String fieldName){
        this.fieldName = fieldName;
        log.info("New Object 'EventField' created");
    }

    /**
     * function that defines what happens when a player steps on the field. a switch statement activate the different event field functions with a random number
     * @param player player who moved on this street
     * @param sendMessage to send messages to players and updates to this street
     * @param SessionIds of all players
     * @param board to get every other field
     * @param sendPlayerData to client
     */
    @Override
    public void moveOnField(Player player, SendMessage sendMessage, String[] SessionIds, Board board, SendPlayerData sendPlayerData) {

        int randomNumber = (int) (Math.random() * 7);
        // switch activate the different event field actions with a random number
        switch (randomNumber){
            case 0:
                playerToGo(sendMessage, SessionIds, player, sendPlayerData, board);
                break;
            case 1:
                playerToParkPlace(sendMessage, SessionIds ,player, sendPlayerData ,board);
                break;
            case 2:
                moneyToFreeParkingTwo(sendMessage, SessionIds, player, board);
               break;
            case 3:
                moneyToFreeParkingOne(sendMessage, SessionIds, player, board);
                break;
            case 4:
                moneyToPlayer(sendMessage,SessionIds, player);
                break;
            case 5:
                playerPaysToBank(sendMessage, SessionIds, player);
                break;
            case 6:
                playerToJail(sendMessage, SessionIds, player, sendPlayerData);
                break;
        }
    }

    /**
     *method move player to go field
     */
    private void playerToGo(SendMessage sendMessage, String[] SessionIds, Player player, SendPlayerData sendPlayerData, Board board){
        sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/eventFieldMessage", "Event Field: Move to go");
        log.info("Event Field: Move player to 'Go' field");
        new Thread(() -> {
            try {
                Thread.sleep(ConstantInteger.MOVE_PLAYER_BREAK_TIME);
                player.setPosition(FieldPosition.GO_FIELD);
                sendPlayerData.sendPlayerToClient();
                board.getField(FieldPosition.GO_FIELD).moveOnField(player, sendMessage, SessionIds, board, sendPlayerData);
            } catch (InterruptedException | JsonProcessingException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     *method move player to park place
     */
    private void playerToParkPlace(SendMessage sendMessage, String[] SessionIds, Player player, SendPlayerData sendPlayerData, Board board){
        sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/eventFieldMessage", "Event Field: Move to Park Place");
        log.info("Event Field: Move player to 'Park Palace' field");
        new Thread(() -> {
            try {
                Thread.sleep(ConstantInteger.MOVE_PLAYER_BREAK_TIME);
                player.setPosition(FieldPosition.PARK_PALACE_FIELD);
                sendPlayerData.sendPlayerToClient();
                board.getField(FieldPosition.PARK_PALACE_FIELD).moveOnField(player, sendMessage, SessionIds, board, sendPlayerData);
            } catch (InterruptedException | JsonProcessingException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     *player pays 2$ to free parking
     */
    private void moneyToFreeParkingTwo(SendMessage sendMessage, String[] SessionIds, Player player, Board board){
        sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/eventFieldMessage", "Event Field: Pay 2$ to Free Parking");
        log.info("Event Field: Player pays 2$ to 'Free Parking'");
        FreeParking freeParking = (FreeParking) board.getField(FieldPosition.FREE_PARKING);
        player.playerPaysMoney(2);
        freeParking.setCredit(2);
        sendMessage.sendToAll("/client/freeParkingCredit", String.valueOf(freeParking.getCredit()));
    }

    /**
     *player pays 1$ to free parking
     */
    private void moneyToFreeParkingOne(SendMessage sendMessage, String[] SessionIds, Player player, Board board){
        sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/eventFieldMessage", "Event Field: Pay 1$ to Free Parking");
        log.info("Event Field: Player pays 1$ to 'Free Parking'");
        FreeParking freeParking = (FreeParking) board.getField(FieldPosition.FREE_PARKING);
        player.playerPaysMoney(1);
        freeParking.setCredit(1);
        sendMessage.sendToAll("/client/freeParkingCredit", String.valueOf(freeParking.getCredit()));
    }

    /**
     *player gets 2$ from the bank
     */
    private void moneyToPlayer(SendMessage sendMessage, String[] SessionIds, Player player){
        sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/eventFieldMessage", "Event Field: You get 2$ from the bank");
        log.info("Event Field: Player gets 2$ from the 'bank'");
        player.playerGetsMoney(2);
    }

    /**
     *player pays 1$ to the bank
     */
    private void playerPaysToBank(SendMessage sendMessage, String[] SessionIds, Player player){
        sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/eventFieldMessage", "Event Field: You pay 1$ to the bank");
        log.info("Event Field: Player pays 1$ to the 'bank'");
        player.playerPaysMoney(1);
    }

    /**
     *method sends player to Jail
     */
    private void playerToJail(SendMessage sendMessage, String[] SessionIds, Player player, SendPlayerData sendPlayerData){
        sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/eventFieldMessage", "Event Field: Go to jail");
        log.info("Event Field: Move player to 'jail'");
        new Thread(() -> {
            try {
                Thread.sleep(ConstantInteger.MOVE_PLAYER_BREAK_TIME);
                player.setPosition(FieldPosition.JAIL_FIELD);
                player.setArrested();
                sendPlayerData.sendPlayerToClient();
            } catch (InterruptedException | JsonProcessingException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }
}