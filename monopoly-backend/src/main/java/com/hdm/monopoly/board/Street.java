package com.hdm.monopoly.board;

import com.hdm.monopoly.sendmessage.SendMessage;
import com.hdm.monopoly.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Street implements Field {
    private static final Logger log = LogManager.getLogger(Street.class);

    private final String streetName;
    private final int price;
    private final int rent;
    private final Color color;
    private Player owner;

    public Street(String streetName, int price, int rent, Color color) {
        this.streetName = streetName;
        this.price = price;
        this.rent = rent;
        this.color = color;
        log.info("New Object 'Street' created");
    }

    @Override
    public void moveOnField(Player player, SendMessage sendMessage, String[] SessionIds) {
        if (owner == null) {
            if(player.getPlayerBankBalance() - price >= 0){

                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/toggleBuyEstateBtn", "false" );
                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", "Buy " + streetName + " for $" + price);
                log.info(player.getName() + " Buy " + streetName + " for " + price);
            }
        } else {
            //player on field has to pay rent to the owner
            if(player != owner){
                player.playerPaysMoney(rent);
                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", "You have to pay $" + rent + "rent to " + owner.getName());
                owner.playerGetsMoney(rent);
                sendMessage.sendToPlayer(SessionIds[owner.getID()], "/client/notification", "You received $" + rent + "rent from " + player.getName());
                log.info(player.getName() + " pays " + rent + " rent to " + owner.getName());
            }
        }
    }

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

}
