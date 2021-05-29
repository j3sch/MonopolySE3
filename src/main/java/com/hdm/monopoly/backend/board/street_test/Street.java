package com.hdm.monopoly.backend.board.street_test;

import com.hdm.monopoly.backend.player_money.Player;

public class Street implements Field {
    private String streetName;
    private Player owner;
    private int price;
    private int rent;
    private Color color;


    public enum Color {
        Brown,
        LightBlue,
        Pink,
        Orange,
        Red,
        Yellow,
        Green,
        DarkBlue
    }

    public Street(String streetName, int price, int rent, Color color) {
        this.streetName = streetName;
        this.owner = null;
        this.price = price;
        this.rent = rent;
        this.color = color;
    }

    public void moveOnField(Player player) {
        if (owner == null) {
            //ask player if he wants to buy that field
        } else {
            //player on field has to pay rent to the owner
        }
    }
}