package com.hdm.monopoly.backend.board.send_message;

import com.hdm.monopoly.backend.board.game_logic.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Notified {

    private final SendMessage sendMessage;
    private final String[] sessionIds;
    private final Game game;

    @Autowired
    public Notified(SendMessage sendMessage, String[] sessionIds, Game game) {
        this.sendMessage = sendMessage;
        this.sessionIds = sessionIds;
        this.game = game;
    }

    public void currentPlayer(String message) {
        sendMessage.sendToPlayer(sessionIds[game.getCurrentPlayerIndex()], "/client/notification", message );
    }

    public void allPlayers(String message) {
        sendMessage.sendToAll("/client/notification", message );
    }

    public void playerXOnTurn() {
        sendMessage.sendToAll("/client/notification", "Player " + game.getCurrentPlayer().getName() + " is on turn");
    }
}
