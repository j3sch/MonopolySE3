package com.hdm.monopoly.backend.board.send_message;

import com.hdm.monopoly.backend.board.game_logic.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivateButton {

    private final SendMessage sendMessage;
    private final String[] sessionIds;
    private final Game game;

    @Autowired
    public ActivateButton(SendMessage sendMessage, String[] sessionIds, Game game) {
        this.sendMessage = sendMessage;
        this.sessionIds = sessionIds;
        this.game = game;
    }

    public void diceNumber() {
        sendMessage.sendToUser(sessionIds[game.getCurrentPlayerIndex()], "/client/toggleDiceNumberBtn", "false" );
    }

    public void buyEstate() {
        sendMessage.sendToUser(sessionIds[game.getCurrentPlayerIndex()], "/client/toggleBuyEstateBtn", "false" );
    }

    public void nextPlayer() {
        sendMessage.sendToUser(sessionIds[game.getCurrentPlayerIndex()], "/client/toggleNextPlayerBtn", "false" );
    }
}
