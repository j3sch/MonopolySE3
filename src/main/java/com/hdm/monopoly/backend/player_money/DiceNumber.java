package com.hdm.monopoly.backend.player_money;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdm.monopoly.backend.board.game_logic.Game;
import com.hdm.monopoly.backend.board.send_message.ActivateButton;
import com.hdm.monopoly.backend.board.send_message.Notified;
import com.hdm.monopoly.backend.board.send_message.SendMessage;
import com.hdm.monopoly.backend.board.send_message.SendPlayerData;
import com.hdm.monopoly.backend.board.streets.Street;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Component("diceNumber")
public class DiceNumber {
    private static Logger log = LogManager.getLogger(DiceNumber.class);

    private final Game game;
    private final SendPlayerData sendPlayerData;
    private final ActivateButton activateButton;
    private final SendMessage sendMessage;
    private final Notified notified;


    @Autowired
    public DiceNumber(Game game, SendPlayerData sendPlayerData, ActivateButton activateButton, SendMessage sendMessage, Notified notified) {
        this.game = game;
        this.sendPlayerData = sendPlayerData;
        this.activateButton = activateButton;
        this.sendMessage = sendMessage;
        this.notified = notified;
        log.debug("New Object 'DiceNumber' created");
    }

    /*
    get message, if player clicked on button
    and deactivate the button.
     */
    @MessageMapping("/diceNumberBtnClicked")
    @SendToUser("/client/toggleDiceNumberBtn")
    public String diceNumberBtnClicked() throws JsonProcessingException {
        int diceNumber = diceRandomNumber();    //maybe to display the result of the dice
        if(game.getCurrentPlayer().getJailTime()>0){
            if(diceNumber == 6){
                game.getCurrentPlayer().getReleased();
            }else{
                game.getCurrentPlayer().jailed();
            }
        }else {
            game.movePlayer(diceNumber);
            sendPlayerData.sendPlayerToClient();
            activateButton.nextPlayer();
        }
//        game.endOfTurn();//maybe not the best moment to change the current player
        return new ObjectMapper().writeValueAsString(true);
    }

    public int diceRandomNumber() {
        return (int)(Math.random() * 6 + 1);
    }

    @MessageMapping("/nextPlayerBtnClicked")
    @SendToUser("/client/toggleNextPlayerBtn")
    public String nextPlayerBtnClicked() throws JsonProcessingException {
        game.endOfTurn();
        return new ObjectMapper().writeValueAsString(true);
    }

    @MessageMapping("/buyEstateBtnClicked")
    @SendToUser("/client/toggleBuyEstateBtn")
    public String buyEstate() throws JsonProcessingException {
        Player player = game.getCurrentPlayer();
        Street street = (Street)game.getBoard().getField(player.getPosition());

        player.PlayerPaysMoney(street.getPrice());
        street.setOwner(player);

        sendMessage.sendToAll("/client/buyEstate", player.getPosition() + " " + player.getColour());
        notified.allPlayers(player.getName() + " bought " + street.getFieldName());
        sendPlayerData.sendPlayerToClient();

        return new ObjectMapper().writeValueAsString(true);
    }
}
