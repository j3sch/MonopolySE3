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

    public void sendPlayerToClient() throws JsonProcessingException {
        sendMessage.sendToAll("/client/playerList", new ObjectMapper().writeValueAsString(players));
        log.info(players);
    }
}
