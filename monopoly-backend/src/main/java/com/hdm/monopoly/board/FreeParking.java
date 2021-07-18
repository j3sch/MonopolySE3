package com.hdm.monopoly.board;

import com.hdm.monopoly.player.Player;
import com.hdm.monopoly.sendmessage.SendMessage;
import com.hdm.monopoly.sendmessage.SendPlayerData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * implemented field type that represents the free parking field
 */
public class FreeParking implements Field{
    private static final Logger log = LogManager.getLogger();

    private final String fieldName;
    private int credit;

    public void setCredit(int amount) {
       credit += amount;
    }

    public int getCredit() {
        return credit;
    }

    public FreeParking(String fieldName){
        this.fieldName = fieldName;
        log.info("New Object 'FreeParking' created");
    }

    /***
     * function that defines what happens when a player steps on the field. if free parking has money, the player gets the money
     * @param player player who moved on this street
     * @param sendMessage to send messages to players and updates to this street
     * @param SessionIds of all players
     * @param board to get every other field
     */
    @Override
    public void moveOnField(Player player, SendMessage sendMessage, String[] SessionIds, Board board, SendPlayerData sendPlayerData) {

        if(credit >= 1){
            sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", "You get " + credit + " from Free Parking");
            player.playerGetsMoney(credit);
            credit = 0;
            sendMessage.sendToAll("/client/freeParkingCredit", String.valueOf(credit));
        } else {
            sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", "There is no money in Free Parking");
        }
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }
}
