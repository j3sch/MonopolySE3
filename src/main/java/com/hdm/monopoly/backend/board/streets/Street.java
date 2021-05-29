package com.hdm.monopoly.backend.board.streets;

import com.hdm.monopoly.backend.player_money.Player;

public class Street implements Fields {
//Eigenschaften
//Properties

    private String streetName;
    private int index;
    private int price;
    private int rent;
    private Color color;
    private Player owner;


    //Konstruktor
//Constructor


    public Street(String streetName, int index, int price, int rent, Color color) {
        this.streetName = streetName;
        this.index = index;
        this.price = price;
        this.rent = rent;
        this.color = color;
    }

    //Methods
    public void moveOnField(Player player) {
        if (owner == null) {
            //ask player if he wants to buy that field
        } else {
            //player on field has to pay rent to the owner
        }
    }
    //Getter
    public String getStreetName() {
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

    public int getIndex() {
        return index;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }



}
