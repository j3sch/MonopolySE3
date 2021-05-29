package com.hdm.monopoly.backend.board.streets;

import java.util.ArrayList;

public class StreetManager {


    //Eigenschaften
//properties
    final private String[]  streetName = {"Go","Burger Joint","Pizza House","Event Field","Candy Store","Ice Cream Palor","Jail","Museum", "Library","Event Field", "Skate Park", "Swimming Pool","Free Parking", "Video Game Arcade", "Movie Theater","Event Field", "Toy Store", "Pet Store","Go To Jail", "Bowling Alley", "The Zoo","Event Field", "Park Place", "Boardwalk"};
    final private int[] index ={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24};
    final private int[] price = {0,1,1,0,1,1,0,2,2,0,2,2,0,3,3,0,3,3,0,4,4,0,5,5};
    final private int[] rent =  {20,1,1,0,1,1,0,2,2,0,2,2,0,3,3,0,3,3,0,4,4,0,5,5};
    final private Color[] color = {null, Color.LightBlue, Color.LightBlue,null, Color.Brown, Color.Brown,null, Color.Pink, Color.Pink,null, Color.Orange, Color.Orange,null, Color.Red, Color.Red,null, Color.Yellow, Color.Yellow,null, Color.Green, Color.Green,null, Color.DarkBlue, Color.DarkBlue};
    ArrayList<Street> streets = new ArrayList();
    //Constructor
    public StreetManager() {
        this.streetMaker();
    }

//Methods


    //For schleife erstellt die Objekte und fügt sie der ArrayListe hinzu.
    public void streetMaker() {
        for(int i = 0; i <= 23; ++i) {
            Street street = new Street(this.streetName[i],this.index[i], this.price[i], this.rent[i], this.color[i]);
            this.streets.add(street);
        }
    }

// Die Methode ermöglicht den Zugriff mit Index auf eine Straße.
//Bsp:  StreetManager streetManager = new StreetManager();
//      Street aktuell = streetManager.getStreet(0);  hier wird auf den Ondex 0 zugegriffen
//      mit aktuelleStraße.getStreetName() bekommt man den Straßennamen des index 0 Burger Joint
//

    public Street getStreet(int index) { return (Street)this.streets.get(index); }

}
