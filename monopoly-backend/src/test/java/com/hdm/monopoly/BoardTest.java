package com.hdm.monopoly;

import com.hdm.monopoly.board.Board;
import com.hdm.monopoly.board.Go;
import com.hdm.monopoly.board.Street;
import com.hdm.monopoly.gameplay.Game;
import com.hdm.monopoly.player.Player;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BoardTest {

    @Autowired
    private Board board;

    @org.junit.Test
    public void streetTest() {
        assertEquals(board.getField(5).getFieldName(), "Ice Cream Palor");
        assertEquals(board.getField(8).getFieldName(), "Library");
        assertEquals(board.getField(14).getFieldName(), "Movie Theater");
        assertEquals(board.getField(20).getFieldName(), "The Zoo");
    }

    @org.junit.Test
    public void streetColorTest() {
        Street street1 = (Street)board.getField(1);
        Street street2 = (Street)board.getField(2);
        assertEquals(street1.getColour(), street2.getColour());

        street1 = (Street)board.getField(4);
        street2 = (Street)board.getField(5);
        assertEquals(street1.getColour(), street2.getColour());

        street1 = (Street)board.getField(7);
        street2 = (Street)board.getField(8);
        assertEquals(street1.getColour(), street2.getColour());

        street1 = (Street)board.getField(10);
        street2 = (Street)board.getField(11);
        assertEquals(street1.getColour(), street2.getColour());

        street1 = (Street)board.getField(13);
        street2 = (Street)board.getField(14);
        assertEquals(street1.getColour(), street2.getColour());

        street1 = (Street)board.getField(16);
        street2 = (Street)board.getField(17);
        assertEquals(street1.getColour(), street2.getColour());

        street1 = (Street)board.getField(19);
        street2 = (Street)board.getField(20);
        assertEquals(street1.getColour(), street2.getColour());

        street1 = (Street)board.getField(22);
        street2 = (Street)board.getField(23);
        assertEquals(street1.getColour(), street2.getColour());

    }

    @org.junit.Test
    public void streetPriceTest() {
        Street street1 = (Street)board.getField(1);
        Street street2 = (Street)board.getField(4);
        Street street3 = (Street)board.getField(7);

        assertEquals(street1.getPrice(), 1);
        assertEquals(street2.getPrice(), 1);
        assertEquals(street3.getPrice(), 2);

    }

    @org.junit.Test
    public void JailTest() {
        assertEquals(board.getField(6).getFieldName(), "Jail");
    }

    @org.junit.Test
    public void EventFieldTest() {
        assertEquals(board.getField(3).getFieldName(), "Event Field");

        assertEquals(board.getField(3).getFieldName(), board.getField(9).getFieldName());
        assertNotEquals(board.getField(3), board.getField(9));

        assertEquals(board.getField(3).getFieldName(), board.getField(15).getFieldName());
        assertNotEquals(board.getField(9), board.getField(15));

        assertEquals(board.getField(3).getFieldName(), board.getField(21).getFieldName());
        assertNotEquals(board.getField(9), board.getField(21));
    }

    @org.junit.Test
    public void FreeParkingTest() {
        assertEquals(board.getField(12).getFieldName(), "Free Parking");
    }

    @org.junit.Test
    public void GoToJailTest() {
        assertEquals(board.getField(18).getFieldName(), "Go To Jail");
    }

    @org.junit.Test
    public void GoTest() {
        Go go = (Go)board.getField(0);
        assertEquals(board.getField(0).getFieldName(), "Go");
        assertEquals(go.getGoValue(), 2);
    }
}
