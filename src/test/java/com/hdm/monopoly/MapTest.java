package com.hdm.monopoly;

import com.hdm.monopoly.backend.board.streets.*;
import com.hdm.monopoly.backend.player_money.Colours;
import com.hdm.monopoly.backend.player_money.Player;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class MapTest {
    Colours colours = new Colours();
    Map map = new Map();
    Player player0 = new Player(0, "player0", colours.getColours(0));
    Player player1 = new Player(1, "player1", colours.getColours(1));

    @Test
    public void FieldTest(){
        map.getField(1).moveOnField(player0);
        assertEquals(map.getField(0).getFieldName(), "Go");
        assertEquals(map.getField(1).getFieldName(), "Burger Joint");



    }

    @Test
    public void moveOnFieldTest(){
        Street street1 = new Street("Go", 1, 1, Color.LightBlue);
        //street1.setOwner(player1);
        street1.setOwner(player0);
        street1.moveOnField(player1);
        //assertEquals(player1.getPlayerBankBalance(), 30);
        //assertEquals(player0.getPlayerBankBalance(), 32);
    }
}
