package com.yarachkin.dock.dock;

import com.yarachkin.dock.entity.Ship;
import com.yarachkin.dock.exception.LogicDockException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class DockTest {
    private Ship firstShip;

    @BeforeMethod
    public void setUp() throws Exception {
        Dock.getInstance().initializeCapacityAndDockContainersCounts(150, 60, 3);
        firstShip = new Ship(1, 40, 20, 20, 15);
    }

    @Test
    public void loadOrUnloadContainersTest() throws Exception {
        Dock.getInstance().loadOrUnloadContainers(firstShip);
        assertEquals(Dock.getInstance().getDockContainersCounts(), 65);
    }

    @Test
    public void sizeFreePiersTest() {
        assertEquals(Dock.getInstance().getFreePiersSize(), 3);
    }

    @Test
    public void sizeFreePiersWhenAcquirePiersTest() throws LogicDockException {
        Dock.getInstance().acquirePier();
        Dock.getInstance().acquirePier();
        Dock.getInstance().acquirePier();
        assertEquals(Dock.getInstance().getFreePiersSize(), 0);
    }

    @Test
    public void sizeBusyPiersWhenAcquirePiersTest() throws LogicDockException {
        Dock.getInstance().acquirePier();
        Dock.getInstance().acquirePier();
        Dock.getInstance().acquirePier();
        assertEquals(Dock.getInstance().getBusyPiersSize(), 3);
    }

    @Test
    public void sizeFreePiersWhenPutPiersTest() throws LogicDockException {
        Pier firstPier = Dock.getInstance().acquirePier();
        Pier secondPier = Dock.getInstance().acquirePier();
        Pier thirdPier = Dock.getInstance().acquirePier();
        Dock.getInstance().putBackPier(firstPier);
        Dock.getInstance().putBackPier(secondPier);
        Dock.getInstance().putBackPier(thirdPier);
        assertEquals(Dock.getInstance().getBusyPiersSize(), 0);
    }
}