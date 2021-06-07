package com.hdm.monopoly.backend.board.streets;

import com.hdm.monopoly.backend.board.send_message.SendMessage;
import com.hdm.monopoly.backend.player_money.Player;

public class Street implements Field {

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

    }

    @Override
    public void moveOnField(Player player, SendMessage sendMessage, String[] SessionIds) {
        if (owner == null) {
            if(player.getPlayerBankBalance()-price >=0){

                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/toggleBuyEstateBtn", "false" );
                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", "Buy " + streetName + " for $" + price);

            }
        } else {
            //player on field has to pay rent to the owner
            if(player != owner){
                player.PlayerPaysMoney(rent);
                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", "You have to pay $" + rent + "rent to " + owner.getName());
                owner.PlayerGetsMoney(rent);
                sendMessage.sendToPlayer(SessionIds[owner.getID()], "/client/notification", "You received $" + rent + "rent from " + player.getName());

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
