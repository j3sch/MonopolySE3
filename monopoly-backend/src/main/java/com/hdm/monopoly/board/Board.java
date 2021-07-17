package com.hdm.monopoly.board;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
/**
 * Class is creating the Game board. Multiple Arrays for the fieldtypes and fieldnames.
 * Also the price, rent, color,... are in Arrays sorted to all the fields.
 *
 */
public class Board {
    private static final Logger log = LogManager.getLogger(Board.class);

    final private String[] fieldType = {"Go","Street","Street","EventField","Street","Street","Jail","Street","Street","EventField","Street","Street","FreeParking","Street","Street","EventField","Street","Street","GoToJail","Street","Street", "EventField", "Street", "Street"};
    final private String[] fieldName = {"Go","Burger Joint","Pizza House","Event Field","Candy Store","Ice Cream Palor","Jail","Museum", "Library","Event Field", "Skate Park", "Swimming Pool", "Free Parking", "Video Game Arcade", "Movie Theater","Event Field", "Toy Store", "Pet Store","Go To Jail", "Bowling Alley", "The Zoo","Event Field", "Park Place", "Boardwalk"};
    final private int[] price = {0,1,1,0,1,1,0,2,2,0,2,2,0,3,3,0,3,3,0,4,4,0,5,5};
    final private int[] rent =  {0,1,1,0,1,1,0,2,2,0,2,2,0,3,3,0,3,3,0,4,4,0,5,5};
    final private int[] goValue = {2,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    final private Colour[] colour = {null, Colour.Brown, Colour.Brown,null, Colour.LightBlue, Colour.LightBlue,null, Colour.Pink, Colour.Pink,null, Colour.Orange, Colour.Orange,null, Colour.Red, Colour.Red,null, Colour.Yellow, Colour.Yellow,null, Colour.Green, Colour.Green,null, Colour.DarkBlue, Colour.DarkBlue};

    private final Field[] board;

    public Board() {
        this.board = new Field[fieldType.length];
        setBoard();
    }

    /**
     * the board gets created and all the fields are implemented into the map in the right order
     */
    private void setBoard(){
        for(int i = 0; i < fieldName.length; ++i) {
            board[i] = FieldFactory.createField(fieldType[i], fieldName[i], price[i], rent[i], colour[i], goValue[i]);
        }
        if (fieldName.length == board.length){
            log.info("Board successfully created");
        }else{
            log.warn("Not all fields are correct implemented");
        }

    }

    public int size(){
        return board.length;
    }

    /**
     *
     * @param position
     * @return Getter for the position
     */
    public Field getField(int position){
        if(position<0 || position>size()){
            log.error("position " + position + " is outside of the Gameboard length!");
        }
        return board[position];
    }
}
