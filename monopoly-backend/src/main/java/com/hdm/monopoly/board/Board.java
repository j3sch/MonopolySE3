package com.hdm.monopoly.board;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Board {
    private static final Logger log = LogManager.getLogger(Board.class);

    final private String[] fieldTypes = {"Go","Street","Street","EventField","Street","Street","Jail","Street","Street","EventField","Street","Street","FreeParking","Street","Street","EventField","Street","Street","GoToJail","Street","Street", "EventField", "Street", "Street"};
    final private String[]  streetName = {"Go","Burger Joint","Pizza House","Event Field","Candy Store","Ice Cream Palor","Jail","Museum", "Library","Event Field", "Skate Park", "Swimming Pool","Free Parking", "Video Game Arcade", "Movie Theater","Event Field", "Toy Store", "Pet Store","Go To Jail", "Bowling Alley", "The Zoo","Event Field", "Park Place", "Boardwalk"};
    final private int[] price = {0,1,1,0,1,1,0,2,2,0,2,2,0,3,3,0,3,3,0,4,4,0,5,5};
    final private int[] rent =  {0,1,1,0,1,1,0,2,2,0,2,2,0,3,3,0,3,3,0,4,4,0,5,5};
    final private int[] goValues = {2,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    final private Colour[] colour = {null, Colour.Brown, Colour.Brown,null, Colour.LightBlue, Colour.LightBlue,null, Colour.Pink, Colour.Pink,null, Colour.Orange, Colour.Orange,null, Colour.Red, Colour.Red,null, Colour.Yellow, Colour.Yellow,null, Colour.Green, Colour.Green,null, Colour.DarkBlue, Colour.DarkBlue};

    private final Field[] board;

    public Board() {
        this.board = new Field[fieldTypes.length];
        setBoard();
    }

    //here the board gets created and all the fields are implemented into the map in the right order
    public void setBoard(){
        for(int i = 0; i < streetName.length; ++i) {
            board[i] = FieldFactory.createField(fieldTypes[i], streetName[i], price[i], rent[i], colour[i],goValues[i]);
        }
        if (streetName.length == board.length){
            log.info("Map successfully created");
        }else{
            log.warn("Not all fields are correct implemented");
        }

    }

    public int size(){
        return board.length;
    }

    public Field getField(int position){
        if(position<0 || position>size()){
            log.error("position " + position + " is outside of the gameboard length ");
        }
        return board[position];
    }
}
