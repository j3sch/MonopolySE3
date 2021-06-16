package com.hdm.monopoly;

import com.hdm.monopoly.board.Map;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MapTest {

    @Autowired
    private Map map;

    @org.junit.Test
    public void streetIceCreamPalor() {
        assertEquals(map.getField(5).getFieldName(), "Ice Cream Palor");
    }
}
