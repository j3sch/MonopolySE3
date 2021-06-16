package com.hdm.monopoly.player;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdm.monopoly.sendmessage.ActivateButton;
import com.hdm.monopoly.sendmessage.Notified;
import com.hdm.monopoly.sendmessage.SendMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Component("createPlayer")
public class CreatePlayers {
    private static final Logger log = LogManager.getLogger(CreatePlayers.class);
    private int playerNumber;
    private final Colours colours = new Colours();
    private Boolean isPartyFull = false;
    private final String[] sessionIds;
    private final Player[] players;
    private final SendMessage sendMessage;
    private final ActivateButton activateButton;
    private final Notified notified;

    @Autowired
    public CreatePlayers(Player[] players, SendMessage sendMessage, String[] sessionIds,
                         ActivateButton activateButton, Notified notified) {
        this.players = players;
        this.sendMessage = sendMessage;
        this.sessionIds = sessionIds;
        this.activateButton = activateButton;
        this.notified = notified;
        log.info("New Object 'CreatePlayers' created");
    }

    /**
     * A new player gets created, if the party isn't full
     * @param message name of the player
     * @param sessionId will be stored in an array to send messages to the player individually
     */
    @MessageMapping("/playerName")
    public void addPlayer(Player message, @Header("simpSessionId") String sessionId)
            throws JsonProcessingException {

        if (playerNumber < 4) {

            sessionIds[playerNumber] = (sessionId);

            players[playerNumber] = new Player(
                    playerNumber,
                    message.getName(),
                    colours.getColours(playerNumber)
            );

            playerNumber++;

            if (playerNumber == 4) {
                isPartyFull = true;
                activateButton.diceNumber();
                notified.playerXOnTurn();
            }
        }
        for (String id: sessionIds) {
            if (id != null) {
                sendMessage.sendToPlayer(id, "/client/playerList", new ObjectMapper().writeValueAsString(players));
            }
        }
    }

    /**
     * gets a message on successful connection and then tells the client if more players can join
     * @return isPartyFull
     */
    @MessageMapping("/isPartyFull")
    @SendToUser("/client/reply")
    public String processMessageFromClient() throws Exception {
        Thread.sleep(1000);

        return new ObjectMapper().writeValueAsString(isPartyFull);
    }
}
