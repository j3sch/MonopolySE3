package com.hdm.monopoly.backend.board.send_message;

import com.hdm.monopoly.backend.board.game_logic.Game;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivateButton {
    private static Logger log = LogManager.getLogger(ActivateButton.class);

    private final SendMessage sendMessage;
    private final String[] sessionIds;
    private final Game game;

    @Autowired
    public ActivateButton(SendMessage sendMessage, String[] sessionIds, Game game) {
        this.sendMessage = sendMessage;
        this.sessionIds = sessionIds;
        this.game = game;
        log.debug("New Object 'ActivateButton' created");
    }

    public void diceNumber() {
        sendMessage.sendToPlayer(sessionIds[game.getCurrentPlayerIndex()], "/client/toggleDiceNumberBtn", "false" );

    }

    public void buyEstate() {
        sendMessage.sendToPlayer(sessionIds[game.getCurrentPlayerIndex()], "/client/toggleBuyEstateBtn", "false" );
    }

    public void nextPlayer() {
        sendMessage.sendToPlayer(sessionIds[game.getCurrentPlayerIndex()], "/client/toggleNextPlayerBtn", "false" );
    }
}
