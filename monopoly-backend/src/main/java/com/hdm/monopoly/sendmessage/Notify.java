package com.hdm.monopoly.sendmessage;

import com.hdm.monopoly.logic.Game;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Notify {
    private static final Logger log = LogManager.getLogger(Notify.class);

    private final SendMessage sendMessage;
    private final String[] sessionIds;
    private final Game game;

    @Autowired
    public Notify(SendMessage sendMessage, String[] sessionIds, Game game) {
        this.sendMessage = sendMessage;
        this.sessionIds = sessionIds;
        this.game = game;
        log.info("New Object 'Notified' created");
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
