package com.hdm.monopoly.board;

import com.hdm.monopoly.sendmessage.SendMessage;
import com.hdm.monopoly.player.Player;
import com.hdm.monopoly.sendmessage.SendPlayerData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * implemented field type that represents the field which tells you that you are now in prison
 */
public class GoToJail implements Field{
    private static final Logger log = LogManager.getLogger(GoToJail.class);

    private final String fieldName;

    public GoToJail(String fieldName){
        this.fieldName = fieldName;
        log.info("New Object 'GoToJail' created");
    }

    /**
     * function that defines what happens when a player steps on the field
     * @param player player who moved on "go to jail"
     * @param sendMessage to send Messages to all Players or a specific player
     * @param SessionIds with this id we can send a message to a specific player
     * @param board let us access the fields
     * @param sendPlayerData to update the player data on client side
     */
    @Override
    public void moveOnField(Player player, SendMessage sendMessage, String[] SessionIds, Board board, SendPlayerData sendPlayerData) {
        player.setPosition(6);
        player.setArrested();
        sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", "You have to go to jail.");
        log.info(player.getName() + " moves into jail");
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }
}
