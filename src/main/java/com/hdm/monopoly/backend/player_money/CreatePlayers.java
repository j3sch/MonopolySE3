package com.hdm.monopoly.backend.player_money;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdm.monopoly.backend.board.send_message.ActivateButton;
import com.hdm.monopoly.backend.board.send_message.Notify;
import com.hdm.monopoly.backend.board.send_message.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Component("createPlayer")
public class CreatePlayers {
    private int playerNumber;
    private final Colours colours = new Colours();
    private Boolean isPartyFull = false;
    private final String[] sessionIds;
    private final Player[] players;
    private final SendMessage sendMessage;
    private final ActivateButton activateButton;
    private final Notify notify;

    @Autowired
    public CreatePlayers(Player[] players, SendMessage sendMessage, String[] sessionIds,
                         ActivateButton activateButton, @Qualifier("getNotify") Notify notify) {
        this.players = players;
        this.sendMessage = sendMessage;
        this.sessionIds = sessionIds;
        this.activateButton = activateButton;
        this.notify = notify;
    }

    /*
    gets the entered player name.
    Creates the player and sends the array of all players to the client.
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
                //activateButton.buyEstate();
                activateButton.diceNumber();
                notify.playerXOnTurn();
            }
        }
        for (String id: sessionIds) {
            if (id != null) {
                sendMessage.sendToPlayer(id, "/client/playerList", new ObjectMapper().writeValueAsString(players));
            }
        }
    }

    /*
    sends this message only to the player whose turn it is now, so that the buttons can be activated
     */
    //Define previous Player for everyone
    public void setPreviousPlayers() {
        for(int i = 0; i < playerNumber; i++){
            if(i!=0) {
                players[i].setPreviousPlayer(players[i-1]);
            }else{
                players[i].setPreviousPlayer(players[playerNumber -1]);
            }
        }
    }

    /*
    gets a message on successful connection and then tells the client if more players can join
    */
    @MessageMapping("/message")
    @SendToUser("/client/reply")
    public String processMessageFromClient() throws Exception {
        Thread.sleep(1000);

        return new ObjectMapper().writeValueAsString(isPartyFull);
    }
}
