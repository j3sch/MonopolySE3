package com.hdm.monopoly.player;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdm.monopoly.gameplay.Countdown;
import com.hdm.monopoly.gameplay.Game;
import com.hdm.monopoly.sendmessage.Notify;
import com.hdm.monopoly.sendmessage.SendMessage;
import com.hdm.monopoly.utility.ConstantInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Component
public class CreatePlayer {
    private static final Logger log = LogManager.getLogger(CreatePlayer.class);
    private int playerNumber;
    private final SwitchPlayerColour SwitchPlayerColour = new SwitchPlayerColour();
    private Boolean isPartyFull = false;
    private final String[] sessionIds;
    private final Player[] players;
    private final SendMessage sendMessage;
    private final Game game;
    private final Notify notify;
    private final Countdown countdown;

    @Autowired
    public CreatePlayer(Player[] players, SendMessage sendMessage, String[] sessionIds,
                        Game game, Notify notify, Countdown countdown) {
        this.players = players;
        this.sendMessage = sendMessage;
        this.sessionIds = sessionIds;
        this.game = game;
        this.notify = notify;
        this.countdown = countdown;
        log.info("New Object 'CreatePlayers' created");
    }

    /**
     * A new player gets created, if the party isn't full
     * @param message name of the player
     * @param sessionId will be stored in an array to send messages to the player individually
     */
    @MessageMapping("/playerName")
    private void addPlayer(Player message, @Header("simpSessionId") String sessionId)
            throws JsonProcessingException {

        if (playerNumber < ConstantInteger.PLAYER_COUNT) {

            sessionIds[playerNumber] = (sessionId);

            players[playerNumber] = new Player(
                    playerNumber,
                    message.getName(),
                    SwitchPlayerColour.getPlayerColour(playerNumber)
            );

            playerNumber++;

            if (playerNumber == ConstantInteger.PLAYER_COUNT) {
                isPartyFull = true;
                activatedDiceNumberBtn();
                notify.playerXOnTurn();
                countdown.startCountdown();
                log.info("Party is now full. Number of players: " + playerNumber);
            }
        }else{
            log.info("Party is already full. Number of players: " + playerNumber);
        }

        for (String id: sessionIds) {
            if (id != null) {
                sendMessage.sendToPlayer(id, "/client/playerList", new ObjectMapper().writeValueAsString(players));
            }
        }
    }

    private void activatedDiceNumberBtn() {
        sendMessage.sendToPlayer(sessionIds[game.getCurrentPlayerIndex()], "/client/toggleDiceNumberBtn", "false" );
        log.info("Dice number button is now active");
    }

    /**
     * gets a message on successful connection and then tells the client if more players can join
     * @return isPartyFull
     */
    @MessageMapping("/isPartyFull")
    @SendToUser("/client/reply")
    private String processMessageFromClient() throws Exception {
        Thread.sleep(1000);

        return new ObjectMapper().writeValueAsString(isPartyFull);
    }
}
