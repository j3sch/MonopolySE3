package com.hdm.monopoly.backend.board.streets;

import org.springframework.stereotype.Component;

@Component("map")
public class Map {

    final private String[] fieldTypes = {"Go","Street","Street","EventField","Street","Street","Jail","Street","Street","EventField","Street","Street","FreeParking","Street","Street","EventField","Street","Street","GoToJail","Street","Street", "EventField", "Street", "Street"};
    final private String[]  streetName = {"Go","Burger Joint","Pizza House","Event Field","Candy Store","Ice Cream Palor","Jail","Museum", "Library","Event Field", "Skate Park", "Swimming Pool","Free Parking", "Video Game Arcade", "Movie Theater","Event Field", "Toy Store", "Pet Store","Go To Jail", "Bowling Alley", "The Zoo","Event Field", "Park Place", "Boardwalk"};
    final private int[] price = {0,1,1,0,1,1,0,2,2,0,2,2,0,3,3,0,3,3,0,4,4,0,5,5};
    final private int[] rent =  {0,1,1,0,1,1,0,2,2,0,2,2,0,3,3,0,3,3,0,4,4,0,5,5};
    final private int[] goValues = {20,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    final private Color[] color = {null, Color.LightBlue, Color.LightBlue,null, Color.Brown, Color.Brown,null, Color.Pink, Color.Pink,null, Color.Orange, Color.Orange,null, Color.Red, Color.Red,null, Color.Yellow, Color.Yellow,null, Color.Green, Color.Green,null, Color.DarkBlue, Color.DarkBlue};

    private Field[] map;

    public Map() {
        this.map = new Field[fieldTypes.length];
        setMap();
    }


    public void setMap(){
        //here the board gets created and all the fields are implemented into the map in the right order
        for(int i = 0; i < streetName.length; ++i) {
            map[i] = FieldFactory.createField(fieldTypes[i], streetName[i], price[i], rent[i], color[i],goValues[i]);
        }
    }

    public int size(){
        return map.length;
    }

    public Field getField(int position){
        if(position<0 || position>size()){
            //here comes the code for error correction
        }
        return map[position];
    }

}
