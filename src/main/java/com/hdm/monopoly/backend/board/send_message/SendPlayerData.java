package com.hdm.monopoly.backend.board.send_message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdm.monopoly.backend.player_money.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("sendPlayerData")
public class SendPlayerData {

    private final SendMessage sendMessage;
    private final Player[] players;

    @Autowired
    public SendPlayerData(@Qualifier("getSendMessage") SendMessage sendMessage, Player[] players) {
        this.sendMessage = sendMessage;
        this.players = players;
    }

    public void sendPlayerToClient() throws JsonProcessingException {
        sendMessage.sendToAll("/client/playerList", new ObjectMapper().writeValueAsString(players));
    }
}
