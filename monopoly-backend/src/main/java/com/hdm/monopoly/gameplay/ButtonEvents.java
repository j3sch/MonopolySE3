package com.hdm.monopoly.gameplay;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdm.monopoly.helper.EstateColourToHex;
import com.hdm.monopoly.player.Player;
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
    private final String[] sessionIds;
    private final SendMessage sendMessage;
    private final Notify notify;
    private final Countdown countdown;

    @Autowired
    public ButtonEvents(Game game, SendPlayerData sendPlayerData, String[] sessionIds,
                        SendMessage sendMessage, Notify notify, Countdown countdown) {
        this.game = game;
        this.sendPlayerData = sendPlayerData;
        this.sessionIds = sessionIds;
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
    private String diceNumberBtnClicked() throws JsonProcessingException {
        int diceNumber = diceRandomNumber();
        log.info(game.getCurrentPlayer().getName() + ": has rolled number " + diceNumber);

        if(game.getCurrentPlayer().getJailTime() > 0) {
            playerInJail(diceNumber);
        }else {
            game.movePlayer(diceNumber);
            sendPlayerData.sendDicedNumber(diceNumber);
            sendPlayerData.sendPlayerToClient();
        }

        activatedNextPlayerBtn();
        return new ObjectMapper().writeValueAsString(true);
    }

    private void playerInJail(int diceNumber) {
        if(diceNumber == 6){
            game.getCurrentPlayer().getReleased();
            notify.currentPlayer("You're free from prison!");
            log.info(game.getCurrentPlayer().getName() + ": has rolled a 6 and is free from jail");
        }else{
            game.getCurrentPlayer().jailed();
            notify.currentPlayer("Your prison time is " + game.getCurrentPlayer().getJailTime());
            log.info(game.getCurrentPlayer().getName() + ": new jail time is " + game.getCurrentPlayer().getJailTime());
        }
    }

    public void activatedNextPlayerBtn() {
        sendMessage.sendToPlayer(sessionIds[game.getCurrentPlayerIndex()], "/client/toggleNextPlayerBtn", "false" );
        log.info("Next player button is now active");
    }

    /**
     *
     * @return random number between 1 and 6
     */
    private int diceRandomNumber() {
        return (int) (Math.random() * 6 + 1);
    }

    /**
     * button is only activated if current Player already diced an number
     * initiates the change to the next player
     * @return true, to deactivate the nextPlayerBtn
     */
    @MessageMapping("/nextPlayerBtnClicked")
    @SendToUser("/client/toggleNextPlayerBtn")
    private String nextPlayerBtnClicked() throws JsonProcessingException {
        countdown.resetCountdown();
        game.endOfTurn();
        log.info("next player is on turn");
        return new ObjectMapper().writeValueAsString(true);
    }

    /**
     * button is only activated if a Player is on a Street and if this street has no owner
     * buys the street on players position
     * @return true, to deactivate the buyEstateBtn
     */
    @MessageMapping("/buyEstateBtnClicked")
    @SendToUser("/client/toggleBuyEstateBtn")
    private String buyEstateBtnClicked() throws JsonProcessingException {
        Player player = game.getCurrentPlayer();
        Street street = (Street)game.getBoard().getField(player.getPosition());
        EstateColourToHex estateColourToHex = new EstateColourToHex();
        String[] packet = new String[4];
        packet[0] = String.valueOf(player.getPosition());
        packet[1] = street.getFieldName();
        packet [2] = player.getColour();
        packet[3] = estateColourToHex.getHexOfColour(street.getColour().toString());

        for(int i = 0; i <= 3; i++){
            if (packet[i] == null) {
                log.info("Packet could not be created correctly, value on Index " + i + " is null" );
                break;}
        }


        player.playerPaysMoney(street.getPrice());
        street.setOwner(player);
        log.info(player.getName() + " pays " + street.getPrice() + " to buy " + street.getFieldName());

        sendMessage.sendToAll("/client/buyEstate", new ObjectMapper().writeValueAsString(packet));
        notify.allPlayers(player.getName() + " bought " + street.getFieldName());
        sendPlayerData.sendPlayerToClient();

        return new ObjectMapper().writeValueAsString(true);
    }
}
