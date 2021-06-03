package com.hdm.monopoly.backend.player_money;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdm.monopoly.backend.board.game_logic.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Component("diceNumber")
public class DiceNumber {


    private final Game game;
    private final SendMessage sendMessage;
    private final String[] sessionIds;
    private final Player[] players;

    @Autowired
    public DiceNumber(Game game, @Qualifier("getSendMessage") SendMessage sendMessage, String[] sessionIds, Player[] players) {
        this.game = game;
        this.sendMessage = sendMessage;
        this.sessionIds = sessionIds;
        this.players = players;

    }


    /*
    get message, if player clicked on button
    and deactivate the button.
     */
    @MessageMapping("/diceNumberBtnClicked")
    @SendToUser("/client/toggleDiceNumberBtn")
    public String diceNumberBtnClicked() throws JsonProcessingException {
        int diceNumber = diceRandomNumber();    //maybe to display the result of the dice
        game.movePlayer(diceNumber);
        sendPlayerToClient();
        activateNextPlayerBtn();
        return new ObjectMapper().writeValueAsString(true);
    }

    private void activateNextPlayerBtn() {
        sendMessage.sendToPlayer(sessionIds[game.getCurrentPlayerIndex()], "/client/toggleNextPlayerBtn", "false");
    }

    public int diceRandomNumber() {
        return (int)(Math.random()*5)+1;
    }

    @MessageMapping("/nextPlayerBtnClicked")
    @SendToUser("/client/toggleNextPlayerBtn")
    public String nextPlayerBtnClicked() throws JsonProcessingException {
        game.endOfTurn();
        return new ObjectMapper().writeValueAsString(true);
    }

    public void sendPlayerToClient() throws JsonProcessingException {
        sendMessage.sendToAll("/client/playerList", new ObjectMapper().writeValueAsString(players));

    }
}
