package com.hdm.monopoly.backend.board.streets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdm.monopoly.backend.board.game_logic.Game;
import com.hdm.monopoly.backend.board.send_message.ActivateButton;
import com.hdm.monopoly.backend.board.send_message.Notified;
import com.hdm.monopoly.backend.board.send_message.SendMessage;
import com.hdm.monopoly.backend.player_money.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Iterator;

/*@Controller
@Component("Street")*/
public class Street implements Field {
    private static Logger log = LogManager.getLogger(Street.class);
//Eigenschaften
//Properties

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
        log.debug("New Object 'Street' created");

    }

    @Override
    public void moveOnField(Player player, SendMessage sendMessage, String[] SessionIds) {
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
                player.PlayerPaysMoney(rentPrice);
                sendMessage.sendToPlayer(SessionIds[player.getID()], "/client/notification", "You have to pay $" + rentPrice + "rent to " + owner.getName());
                owner.PlayerGetsMoney(rentPrice);
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
        Iterator<Street> streetIterator = this.owner.getOwnedStreets().iterator();
        int colorCount = 0;
        while (streetIterator.hasNext()){
            if(streetIterator.next().getColor() == this.color){
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

    public Color getColor() {
        return color;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
        this.owner.addStreet(this);
    }

}
