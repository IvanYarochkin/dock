package com.yarachkin.dock.dock;

import com.yarachkin.dock.entity.Ship;
import com.yarachkin.dock.exception.LogicDockException;

public class Pier {
    Pier() {
    }

    public void loadOrUnloadContainers(Ship ship) throws LogicDockException {
        Dock.getInstance().loadOrUnloadContainers(ship);
    }

    public void close() throws LogicDockException {
        Dock.getInstance().putBackPier(this);
    }
}
