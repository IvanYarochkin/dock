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

        Ship firstShip = new Ship();
        firstShip.setShipId(1);
        firstShip.setCapacity(30);
        firstShip.setBoardContainersCounts(5);
        firstShip.setLoadingContainersCounts(3);
        firstShip.setUploadingContainersCounts(15);

        Ship secondShip = new Ship();
        secondShip.setShipId(1);
        secondShip.setCapacity(15);
        secondShip.setBoardContainersCounts(5);
        secondShip.setLoadingContainersCounts(5);
        secondShip.setUploadingContainersCounts(15);

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
}