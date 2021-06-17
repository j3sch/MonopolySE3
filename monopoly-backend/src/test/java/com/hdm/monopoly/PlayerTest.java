package com.hdm.monopoly;

import com.hdm.monopoly.player.Colours;
import com.hdm.monopoly.player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class PlayerTest {
    Colours colours = new Colours();

    Player Player1 = new Player(0, "Spieler1",colours.getColours(0) );
    Player Player2 = new Player(1, "Spieler2",colours.getColours(1) );
    Player Player3 = new Player(2, "Spieler3",colours.getColours(2) );
    Player Player4 = new Player(3, "Spieler4",colours.getColours(3) );

    @Test
    public void MoneyTest(){
        assertEquals(Player1.getPlayerBankBalance(), 31);

        Player2.playerGetsMoney(2);
        assertEquals(Player2.getPlayerBankBalance(), 33);
        assertNotEquals(Player3.getPlayerBankBalance(), 33);

        Player3.playerPaysMoney(2);
        assertEquals(Player3.getPlayerBankBalance(), 29);
        assertNotEquals(Player4.getPlayerBankBalance(), 29);

        Player3.playerGetsMoney(4);
        assertEquals(Player3.getPlayerBankBalance(), 33);
    }

    @Test
    public void CreatePlayersTest(){
        assertEquals(Player1.getPosition(), 0);

        Player2.setPosition(2);
        assertEquals(Player2.getPosition(), 2);
        assertNotEquals(Player3.getPosition(), 2);
    }

    @Test
    public void ColoursTest(){
        assertEquals(Player1.getColour(), "#f44336");
        assertEquals(Player2.getColour(), "#2196f3");
        assertEquals(Player3.getColour(), "#4caf50");
        assertEquals(Player4.getColour(), "#FFEB3B");

    }


}
