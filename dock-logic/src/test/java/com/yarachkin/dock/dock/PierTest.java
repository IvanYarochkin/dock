package com.yarachkin.dock.dock;

import com.yarachkin.dock.entity.Ship;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PierTest {
    private Ship ship;
    private Pier pier;

    @BeforeMethod
    public void setUp() throws Exception {
        Dock.getInstance().initializeCapacityAndDockContainersCounts(150, 60);
        ship = new Ship(1, 40, 20, 10, 10);
        pier = new Pier();
    }

    @Test
    public void loadContainersTest() throws Exception {
        pier.loadContainers(ship);
        assertEquals(Dock.getInstance().getDockContainersCounts(), 70);
    }

    @Test
    public void UnloadContainersTest() throws Exception {
        pier.unloadContainers(ship);
        assertEquals(Dock.getInstance().getDockContainersCounts(), 50);
    }
}