package com.hdm.monopoly.backend.board.streets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdm.monopoly.backend.board.game_logic.Game;
import com.hdm.monopoly.backend.board.send_message.ActivateButton;
import com.hdm.monopoly.backend.board.send_message.Notified;
import com.hdm.monopoly.backend.board.send_message.SendMessage;
import com.hdm.monopoly.backend.board.send_message.SendPlayerData;
import com.hdm.monopoly.backend.player_money.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Component
public class BuyStreet {
    SendMessage sendMessage;
    String[] SessionIDs;
    Map map;
    Game game;
    SendPlayerData sendPlayerData;
    Notified notified;
    ActivateButton activateButton;
    Player player;
    Street street;
@Autowired
    public BuyStreet(SendMessage sendMessage, String[] sessionIDs, Map map, Game game, SendPlayerData sendPlayerData, Notified notified, ActivateButton activateButton) {
        this.sendMessage = sendMessage;
        SessionIDs = sessionIDs;
        this.map = map;
        this.game = game;
        this.sendPlayerData = sendPlayerData;
        this.notified = notified;
        this.activateButton = activateButton;
    }

    public void buy(){
        player = game.getCurrentPlayer();
        street = map.getStreet(player.getPosition());
        activateButton.buyEstate();
        notified.currentPlayer("Buy " + street.getFieldName() + " for $" + street.getPrice());

    }

    @MessageMapping("/buyEstateBtnClicked")
    @SendToUser("/client/toggleBuyEstateBtn")
    public String buyEstate() throws JsonProcessingException {
        player = game.getCurrentPlayer();
        street = map.getStreet(player.getPosition());
        System.out.println("test");
        System.out.println(street.getPrice());
        player.PlayerPaysMoney(street.getPrice());
        street.setOwner(player);
        sendMessage.sendToAll("/client/buyEstate", player.getPosition() + " " + player.getColour());
        sendMessage.sendToAll("/client/notification", player.getName() + " bought " + street.getFieldName());
        sendPlayerData.sendPlayerToClient();

        return new ObjectMapper().writeValueAsString(true);
    }


}
