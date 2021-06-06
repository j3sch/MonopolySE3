package com.hdm.monopoly.backend.board.streets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdm.monopoly.backend.board.game_logic.Game;
import com.hdm.monopoly.backend.board.send_message.ActivateButton;
import com.hdm.monopoly.backend.board.send_message.SendMessage;
import com.hdm.monopoly.backend.board.send_message.SendPlayerData;
import com.hdm.monopoly.backend.di.GameConfig;
import com.hdm.monopoly.backend.player_money.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/*@Controller
@Component("Street")*/
public class Street implements Field {
//Eigenschaften
//Properties

    private String streetName;
    private int price;
    private int rent;
    private Color color;
    private Player owner;
    private Player currentPlayer;
    /*SendMessage sendMessage = GameConfig.getSendMessage();
    String[] sessionIds = GameConfig.getSessionIds();
    ActivateButton activateButton = GameConfig.getActivateButton();
    SendPlayerData sendPlayerData = GameConfig.getSendPlayerData();*/
    BuyStreet buyStreet = GameConfig.getBuyStreet();
    /*private Game game = gameConfig.getGame();
    private Map map = gameConfig.getMap();*/



    //Konstruktor
//Constructor

    /*@Autowired
    public Street(SendMessage sendMessage, String[] sessionIds, Game game, Map map) {
        this.sendMessage = sendMessage;
        this.sessionIds = sessionIds;
        this.game = game;
        this.map = map;
    }*/

    public Street(String streetName, int price, int rent, Color color) {
        this.streetName = streetName;
        this.price = price;
        this.rent = rent;
        this.color = color;
    }

    //Methods
    @Override
    public void moveOnField(Player player) {
        currentPlayer = player;
        if (owner == null) {
            if(currentPlayer.getPlayerBankBalance()-price >=0){
                buyStreet.buy();
                /*activateButton.buyEstate();
                sendMessage.sendToPlayer(sessionIds[currentPlayer.getID()], "/client/notification", "Buy " + streetName + " for $" + price);*/
            }
        } else {
            //player on field has to pay rent to the owner
            if(currentPlayer != owner){
                /*currentPlayer.PlayerPaysMoney(rent);
                sendMessage.sendToPlayer(sessionIds[currentPlayer.getID()], "/client/notification", "You have to pay $" + rent + "rent to " + owner.getName());
                owner.PlayerGetsMoney(rent);
                sendMessage.sendToPlayer(sessionIds[owner.getID()], "/client/notification", "You received $" + rent + "rent from " + currentPlayer.getName());
*/
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

/*    @MessageMapping("/buyEstateBtnClicked")
    @SendToUser("/client/toggleBuyEstateBtn")
    public String buyEstate() throws JsonProcessingException {
        System.out.println("test");
        currentPlayer.PlayerPaysMoney(getPrice());
        setOwner(currentPlayer);
        sendMessage.sendToAll("/client/buyEstate", currentPlayer.getPosition() + " " + currentPlayer.getColour());
        sendMessage.sendToAll("/client/notification", currentPlayer.getName() + " bought " + streetName);
        sendPlayerData.sendPlayerToClient();

        return new ObjectMapper().writeValueAsString(true);
    }*/



}
