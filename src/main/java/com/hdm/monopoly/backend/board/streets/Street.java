package com.hdm.monopoly.backend.board.streets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdm.monopoly.backend.board.game_logic.Game;
import com.hdm.monopoly.backend.board.send_message.ActivateButton;
import com.hdm.monopoly.backend.board.send_message.Notified;
import com.hdm.monopoly.backend.board.send_message.SendMessage;
import com.hdm.monopoly.backend.player_money.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


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

    private Player currentPlayer;
    ActivateButton activateButton;
    Notified notified;



    //Konstruktor
//Constructor

    /*@Autowired
    public Street(@Qualifier("getSendMessage") SendMessage sendMessage, @Qualifier("getSessionIds") String[] sessionIds, @Qualifier("getActivateButton") ActivateButton activateButton, @Qualifier("getNotified") Notified notified){
        this.sendMessage = sendMessage;
        this.sessionIds = sessionIds;
        this.activateButton = activateButton;
        this.notified = notified;

    }*/

    public Street(String streetName, int price, int rent, Color color, SendMessage sendmessage, String[] SessionIds) {
        this.streetName = streetName;
        this.price = price;
        this.rent = rent;
        this.color = color;
        this.sendMessage = sendmessage;
        this.sessionIds = SessionIds;
    }

    //Methods
    @Override
    public void moveOnField(Player player) {

        currentPlayer = player;
        if (owner == null) {
            if(player.getPlayerBankBalance()-price >=0){
                //notified.currentPlayer("Buy " + streetName + " for $" + price);
                sendMessage.sendToAll("/client/notification", "Buy " + streetName + " for $" + price);//hier eigentlich nur an einen Spieler, funktioniert aber noch nicht
                sendMessage.sendToUser(sessionIds[currentPlayer.getID()], "/client/toggleBuyEstateBtn", "false" );

                System.out.println("test");


            }

        } else {
            //player on field has to pay rent to the owner
            if(player != owner){
                player.PlayerPaysMoney(rent);
                sendMessage.sendToUser(sessionIds[currentPlayer.getID()], "/client/notification", "You have to pay $" + rent + "rent to " + owner.getName());
                owner.PlayerGetsMoney(rent);
                sendMessage.sendToUser(sessionIds[owner.getID()], "/client/notification", "You received $" + rent + "rent from " + player.getName());

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
    public String buyEstateBtnClicked()
            throws JsonProcessingException {
        System.out.println("Test2");
        currentPlayer.PlayerPaysMoney(getPrice());
        setOwner(currentPlayer);
        System.out.println(owner.getName());

        sendMessage.sendToAll("/client/notification", currentPlayer.getName() + " bought " + streetName);
        return new ObjectMapper().writeValueAsString(true);


    }



}
