package com.hdm.monopoly.backend.board.street_test;

public class Map {
    private Field[] map;

    public Map(){
        //here the board gets created and all the fields are implemented into the map in the right order
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
