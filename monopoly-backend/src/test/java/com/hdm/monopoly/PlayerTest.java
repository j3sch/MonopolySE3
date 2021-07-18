package com.hdm.monopoly;

import com.hdm.monopoly.player.SwitchPlayerColour;
import com.hdm.monopoly.player.Player;
import com.hdm.monopoly.utility.PlayerColour;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    SwitchPlayerColour SwitchPlayerColour = new SwitchPlayerColour();

    Player Player1 = new Player(0, "Spieler1", SwitchPlayerColour.getPlayerColour(0) );
    Player Player2 = new Player(1, "Spieler2", SwitchPlayerColour.getPlayerColour(1) );
    Player Player3 = new Player(2, "Spieler3", SwitchPlayerColour.getPlayerColour(2) );
    Player Player4 = new Player(3, "Spieler4", SwitchPlayerColour.getPlayerColour(3) );

    @Test
    public void MoneyTest(){
        Assertions.assertEquals(Player1.getPlayerBankBalance(), 31);

        Player2.playerGetsMoney(2);
        Assertions.assertEquals(Player2.getPlayerBankBalance(), 33);
        Assertions.assertNotEquals(Player3.getPlayerBankBalance(), 33);

        Player3.playerPaysMoney(2);
        Assertions.assertEquals(Player3.getPlayerBankBalance(), 29);
        Assertions.assertNotEquals(Player4.getPlayerBankBalance(), 29);

        Player3.playerGetsMoney(4);
        Assertions.assertEquals(Player3.getPlayerBankBalance(), 33);
    }

    @Test
    public void CreatePlayersTest(){
        Assertions.assertEquals(Player1.getPosition(), 0);

        Player2.setPosition(2);
        Assertions.assertEquals(Player2.getPosition(), 2);
        Assertions.assertNotEquals(Player3.getPosition(), 2);
    }

    @Test
    public void ColoursTest(){
        Assertions.assertEquals(Player1.getColour(), PlayerColour.RED);
        Assertions.assertEquals(Player2.getColour(), PlayerColour.BLUE);
        Assertions.assertEquals(Player3.getColour(), PlayerColour.GREEN);
        Assertions.assertEquals(Player4.getColour(), PlayerColour.ORANGE);

    }
}
