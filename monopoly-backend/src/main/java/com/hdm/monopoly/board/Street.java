package com.hdm.monopoly.board;

import com.hdm.monopoly.sendmessage.SendMessage;
import com.hdm.monopoly.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Iterator;

public class Street implements Field {
    private static final Logger log = LogManager.getLogger(Street.class);

    private final String streetName;
    private final int price;
    private final int rent;
    private final Colour colour;
    private Player owner;

    public Street(String streetName, int price, int rent, Colour colour) {
        this.streetName = streetName;
        this.price = price;
        this.rent = rent;
        this.colour = colour;
        log.info("New Object 'Street' created");
    }

    @Override
    public void moveOnField(Player player, SendMessage sendMessage, String[] SessionIds, Board board) {

        if (owner == null) {
            if(player.getPlayerBankBalance()-price >=0){

                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/toggleBuyEstateBtn", "false" );
                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", "Buy " + streetName + " for $" + price);
                log.info(player.getName() + " Buy " + streetName + " for " + price);
            }
        } else {
            //player on field has to pay rent to the owner
            if(player != owner){
                int rentPrice = rent;

                //if the owner owns all Streets of the same color double the rent price
                if(colorCheck()){
                    rentPrice *= 2;
                }
                player.playerPaysMoney(rentPrice);
                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", "You have to pay $" + rentPrice + "rent to " + owner.getName());
                owner.playerGetsMoney(rentPrice);
                sendMessage.sendToPlayer(SessionIds[owner.getID()], "/client/notification", "You received $" + rentPrice + "rent from " + player.getName());
                log.info(player.getName() + " pays " + rentPrice + " rent to " + owner.getName());
            }
        }
    }

    /**
     *
     * @return true if owner owns all streets with the same color
     */
    public boolean colorCheck(){
        Iterator<Colour> streetIterator = this.owner.getOwnedColors().iterator();
        int colorCount = 0;
        while (streetIterator.hasNext()){
            if(streetIterator.next() == this.colour){
                if(++colorCount == 2){
                    return true;
                }
            }
        }
        return false;
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

    public Colour getColour() {
        return colour;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player newOwner) {
        if(newOwner!=owner){
            this.owner = newOwner;
            this.owner.addStreet(this.colour);
        }
    }
}
