package com.hdm.monopoly.backend.board.streets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdm.monopoly.backend.board.game_logic.Game;
import com.hdm.monopoly.backend.board.send_message.SendMessage;
import com.hdm.monopoly.backend.player_money.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Component("Street")
public class Street implements Field {
//Eigenschaften
//Properties

    private String streetName;
    private int price;
    private int rent;
    private Color color;
    private Player owner;
    SendMessage sendMessage;
    String[] sessionIds;
    private Game game;
    private Map map;

    //Konstruktor
//Constructor

    @Autowired
    public Street(SendMessage sendMessage, String[] sessionIds, Game game, Map map) {
        this.sendMessage = sendMessage;
        this.sessionIds = sessionIds;
        this.game = game;
        this.map = map;
    }

    public Street(String streetName, int price, int rent, Color color) {
        this.streetName = streetName;
        this.price = price;
        this.rent = rent;
        this.color = color;
    }

    //Methods
    @Override
    public void moveOnField(Player player) {
        if (owner == null) {
            if(game.getCurrentPlayer().getPlayerBankBalance()-price >=0){
                sendMessage.sendToPlayer(sessionIds[game.getCurrentPlayer().getID()], "/client/notification", "Buy " + streetName + " for $" + price);
            }
        } else {
            //player on field has to pay rent to the owner
            if(game.getCurrentPlayer() != owner){
                game.getCurrentPlayer().PlayerPaysMoney(rent);
                sendMessage.sendToPlayer(sessionIds[game.getCurrentPlayer().getID()], "/client/notification", "You have to pay $" + rent + "rent to " + owner.getName());
                owner.PlayerGetsMoney(rent);
                sendMessage.sendToPlayer(sessionIds[owner.getID()], "/client/notification", "You received $" + rent + "rent from " + game.getCurrentPlayer().getName());

            }
        }
    }
    //Getter
    @Override
    public String getFieldName() {
        return streetName;
    }

    public int getPrice() {
        return price;
    }

    public int getRent() {
        return rent;
    }

    public Color getColor() {
        return color;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    @MessageMapping("/buyEstateBtnClicked")
    @SendToUser("/client/toggleBuyEstateBtn")
    public String buyEstate() throws JsonProcessingException {
        System.out.println("test");
        game.getCurrentPlayer().PlayerPaysMoney(getPrice());
        setOwner(game.getCurrentPlayer());
        sendMessage.sendToAll("/client/buyEstate", game.getCurrentPlayer().getPosition() + " " + game.getCurrentPlayer().getColour());
        sendMessage.sendToAll("/client/notification", game.getCurrentPlayer().getName() + " bought " + map.getField(game.getCurrentPlayer().getPosition()).getFieldName());

        return new ObjectMapper().writeValueAsString(true);
    }



}
