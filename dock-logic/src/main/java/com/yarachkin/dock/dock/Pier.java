package com.yarachkin.dock.dock;

import com.yarachkin.dock.entity.Ship;

class Pier {

    public void loadContainers(Ship ship) {
        int dockContainers = Dock.getInstance().getDockContainersCounts();
        int loadingAvailableContainers = Dock.getInstance().getCapacity() - dockContainers;
        int unloadedFromShipContainers = ship.unloadFromShipContainers(loadingAvailableContainers);
        Dock.getInstance().setDockContainersCounts(unloadedFromShipContainers + dockContainers);
    }

    public void unloadContainers(Ship ship) {
        int unloadingAvailable = Dock.getInstance().getDockContainersCounts();
        int loadingInShipContainers = ship.loadInShipContainers(unloadingAvailable);
        Dock.getInstance().setDockContainersCounts(unloadingAvailable - loadingInShipContainers);
    }
}
