package com.hdm.monopoly.backend.board.streets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hdm.monopoly.backend.board.send_message.SendMessage;
import com.hdm.monopoly.backend.player_money.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
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
    private Player currentPlayer;



    //Konstruktor
//Constructor

    @Autowired
    public Street(SendMessage sendMessage, String[] sessionIds) {
        this.sendMessage = sendMessage;
        this.sessionIds = sessionIds;
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
        currentPlayer = player;
        if (owner == null) {
            if(player.getPlayerBankBalance()-price >=0){
                sendMessage.sendToPlayer(sessionIds[currentPlayer.getID()], "/client/notification", "Buy " + streetName + " for $" + price);
            }

        } else {
            //player on field has to pay rent to the owner
            if(player != owner){
                player.PlayerPaysMoney(rent);
                sendMessage.sendToPlayer(sessionIds[currentPlayer.getID()], "/client/notification", "You have to pay $" + rent + "rent to " + owner.getName());
                owner.PlayerGetsMoney(rent);
                sendMessage.sendToPlayer(sessionIds[owner.getID()], "/client/notification", "You received $" + rent + "rent from " + player.getName());

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

    @MessageMapping("/buyEstate")
    public void buyEstate()
            throws JsonProcessingException {
        currentPlayer.PlayerPaysMoney(getPrice());
        setOwner(currentPlayer);

        sendMessage.sendToAll("/client/notification", currentPlayer.getName() + " bought " + streetName);

    }



}
