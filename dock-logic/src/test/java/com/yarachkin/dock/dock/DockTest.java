package com.yarachkin.dock.dock;

import com.yarachkin.dock.entity.Ship;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.testng.Assert.assertEquals;

public class DockTest {
    private Ship ship;

    @BeforeMethod
    public void setUp() throws Exception {
        Lock lock = new ReentrantLock();
        Dock.getInstance().initializeCapacityAndDockContainersCounts(150, 60, 3);
        ship = new Ship(1, 40, 20, 20, 15, lock);
    }

    @Test
    public void loadOrUnloadContainersTest() throws Exception {
        Dock.getInstance().loadOrUnloadContainers(ship);
        assertEquals(Dock.getInstance().getDockContainersCounts(), 65);
    }

}