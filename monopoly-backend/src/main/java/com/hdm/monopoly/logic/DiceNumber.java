package com.hdm.monopoly.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdm.monopoly.player.Player;
import com.hdm.monopoly.sendmessage.ActivateButton;
import com.hdm.monopoly.sendmessage.Notify;
import com.hdm.monopoly.sendmessage.SendMessage;
import com.hdm.monopoly.sendmessage.SendPlayerData;
import com.hdm.monopoly.board.Street;
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
    private static final Logger log = LogManager.getLogger(DiceNumber.class);

    private final Game game;
    private final SendPlayerData sendPlayerData;
    private final ActivateButton activateButton;
    private final SendMessage sendMessage;
    private final Notify notify;

    @Autowired
    public DiceNumber(Game game, SendPlayerData sendPlayerData, ActivateButton activateButton, SendMessage sendMessage, Notify notify) {
        this.game = game;
        this.sendPlayerData = sendPlayerData;
        this.activateButton = activateButton;
        this.sendMessage = sendMessage;
        this.notify = notify;
        log.info("New Object 'DiceNumber' created");
    }

    /**
     * player rolls a number
     * @return true, to deactivate the diceNumberBtn
     */
    @MessageMapping("/diceNumberBtnClicked")
    @SendToUser("/client/toggleDiceNumberBtn")
    public String diceNumberBtnClicked() throws JsonProcessingException {
        int diceNumber = diceRandomNumber();    //maybe to display the result of the dice
        log.info(game.getCurrentPlayer().getName() + " has rolled number " + diceNumber);
        if(game.getCurrentPlayer().getJailTime() > 0) {
            if(diceNumber == 6){
                game.getCurrentPlayer().getReleased();
                notify.currentPlayer("You're free from prison!");
            }else{
                game.getCurrentPlayer().jailed();
                notify.currentPlayer("Your prison time is " + game.getCurrentPlayer().getJailTime());
            }
        }else {
            game.movePlayer(diceNumber);
            sendPlayerData.sendPlayerToClient();
        }
        activateButton.nextPlayer();
        return new ObjectMapper().writeValueAsString(true);
    }

    /**
     *
     * @return random number between 1 and 6
     */
    public int diceRandomNumber() {
        return (int)(Math.random() * 6 + 1);
    }

    /**
     * initiates the change to the next player
     * @return true, to deactivate the nextPlayerBtn
     */
    @MessageMapping("/nextPlayerBtnClicked")
    @SendToUser("/client/toggleNextPlayerBtn")
    public String nextPlayerBtnClicked() throws JsonProcessingException {
        game.endOfTurn();
        return new ObjectMapper().writeValueAsString(true);
    }

    /**
     * buys the street on players position
     * @return true, to deactivate the buyEstateBtn
     */
    @MessageMapping("/buyEstateBtnClicked")
    @SendToUser("/client/toggleBuyEstateBtn")
    public String buyEstate() throws JsonProcessingException {
        Player player = game.getCurrentPlayer();
        Street street = (Street)game.getMap().getField(player.getPosition());

        player.playerPaysMoney(street.getPrice());
        street.setOwner(player);

        sendMessage.sendToAll("/client/buyEstate", player.getPosition() + " " + player.getColour());
        notify.allPlayers(player.getName() + " bought " + street.getFieldName());
        sendPlayerData.sendPlayerToClient();

        return new ObjectMapper().writeValueAsString(true);
    }
}
