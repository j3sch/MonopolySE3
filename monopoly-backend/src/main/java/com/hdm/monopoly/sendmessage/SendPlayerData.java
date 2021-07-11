package com.hdm.monopoly.sendmessage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdm.monopoly.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("sendPlayerData")
public class SendPlayerData {
    private static final Logger log = LogManager.getLogger(SendPlayerData.class);

    private final SendMessage sendMessage;
    private final Player[] players;

    @Autowired
    public SendPlayerData(SendMessage sendMessage, Player[] players) {
        this.sendMessage = sendMessage;
        this.players = players;
    }

    /**
     * when a player is changed, this method can be called to send the new changes to the client
     */
    public void sendPlayerToClient() throws JsonProcessingException {
        sendMessage.sendToAll("/client/playerList", new ObjectMapper().writeValueAsString(players));
        log.info(players);
    }

    public void sendDicedNumber(int dicedNumber) throws JsonProcessingException {
        sendMessage.sendToAll("/client/diceNumber", new ObjectMapper().writeValueAsString(dicedNumber));
    }
}
