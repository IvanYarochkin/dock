package com.yarachkin.dock.dock;

import com.yarachkin.dock.entity.Ship;

class Pier {

    public void loadOrUnloadContainers(Ship ship) {
        Dock.getInstance().loadOrUnloadContainers(ship);
    }
}
