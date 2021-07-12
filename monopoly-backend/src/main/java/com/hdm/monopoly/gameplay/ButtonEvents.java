package com.hdm.monopoly.gameplay;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdm.monopoly.helper.EstateColourToHex;
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
@Component
public class ButtonEvents {
    private static final Logger log = LogManager.getLogger(ButtonEvents.class);

    private final Game game;
    private final SendPlayerData sendPlayerData;
    private final ActivateButton activateButton;
    private final SendMessage sendMessage;
    private final Notify notify;
    private final Countdown countdown;

    @Autowired
    public ButtonEvents(Game game, SendPlayerData sendPlayerData, ActivateButton activateButton,
                        SendMessage sendMessage, Notify notify, Countdown countdown) {
        this.game = game;
        this.sendPlayerData = sendPlayerData;
        this.activateButton = activateButton;
        this.sendMessage = sendMessage;
        this.notify = notify;
        this.countdown = countdown;
        log.info("New Object 'DiceNumber' created");
    }

    /**
     * button is only activated for the player who is on turn
     * player rolls a number
     * @return true, to deactivate the diceNumberBtn
     */
    @MessageMapping("/diceNumberBtnClicked")
    @SendToUser("/client/toggleDiceNumberBtn")
    public String diceNumberBtnClicked() throws JsonProcessingException {
        int diceNumber = diceRandomNumber();
        log.info(game.getCurrentPlayer().getName() + " has rolled number " + diceNumber);

        if(game.getCurrentPlayer().getJailTime() > 0) {
            playerInJail(diceNumber);
        }else {
            game.movePlayer(diceNumber);
            sendPlayerData.sendDicedNumber(diceNumber);
            sendPlayerData.sendPlayerToClient();
        }

        activateButton.nextPlayer();
        return new ObjectMapper().writeValueAsString(true);
    }

    private void playerInJail(int diceNumber) {
        if(diceNumber == 6){
            game.getCurrentPlayer().getReleased();
            notify.currentPlayer("You're free from prison!");
        }else{
            game.getCurrentPlayer().jailed();
            notify.currentPlayer("Your prison time is " + game.getCurrentPlayer().getJailTime());
        }
    }

    /**
     *
     * @return random number between 1 and 6
     */
    private int diceRandomNumber() {
        return 3;
    }

    /**
     * button is only activated if current Player already diced an number
     * initiates the change to the next player
     * @return true, to deactivate the nextPlayerBtn
     */
    @MessageMapping("/nextPlayerBtnClicked")
    @SendToUser("/client/toggleNextPlayerBtn")
    public String nextPlayerBtnClicked() throws JsonProcessingException {
        countdown.resetCountdown();
        game.endOfTurn();
        return new ObjectMapper().writeValueAsString(true);
    }

    /**
     * button is only activated if a Player is on a Street and if this street has no owner
     * buys the street on players position
     * @return true, to deactivate the buyEstateBtn
     */
    @MessageMapping("/buyEstateBtnClicked")
    @SendToUser("/client/toggleBuyEstateBtn")
    public String buyEstateBtnClicked() throws JsonProcessingException {
        Player player = game.getCurrentPlayer();
        Street street = (Street)game.getBoard().getField(player.getPosition());
        EstateColourToHex estateColourToHex = new EstateColourToHex();
        String[] packet = new String[4];
        packet[0] = String.valueOf(player.getPosition());
        packet[1] = street.getFieldName();
        packet [2] = player.getColour();
        packet[3] = estateColourToHex.getHexOfColour(street.getColour().toString());


        player.playerPaysMoney(street.getPrice());
        street.setOwner(player);

        sendMessage.sendToAll("/client/buyEstate", new ObjectMapper().writeValueAsString(packet));
        notify.allPlayers(player.getName() + " bought " + street.getFieldName());
        sendPlayerData.sendPlayerToClient();

        return new ObjectMapper().writeValueAsString(true);
    }
}
