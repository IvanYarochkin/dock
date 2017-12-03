package com.yarachkin.dock.util;

import com.yarachkin.dock.entity.Ship;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;

public class DockParserTest {
    private ArrayList<String> inputDate;
    private ArrayList<Ship> result;

    @BeforeMethod
    public void setUp() {
        IdGenerator.setIsTest(true);

        inputDate = new ArrayList<>();
        result = new ArrayList<>();

        inputDate.add("30 5 3 15");
        inputDate.add("15 5 5 15");

        Ship firstShip = new Ship(1, 30, 5, 3, 15);

        Ship secondShip = new Ship(1, 15, 5, 5, 15);

        result.add(firstShip);
        result.add(secondShip);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        IdGenerator.setIsTest(false);
    }


    @Test
    public void parseTest() throws Exception {
        assertEquals(DockParser.parse(inputDate), result);
    }

    @Test
    public void parseMoreValueInLineThanFourTest() throws Exception {
        inputDate.add("50 30 20 20 30");
        assertEquals(DockParser.parse(inputDate), result);
    }

    @Test
    public void parseLessValueInLineThanFourTest() throws Exception {
        inputDate.add("50 30 20");
        assertEquals(DockParser.parse(inputDate), result);
    }
}