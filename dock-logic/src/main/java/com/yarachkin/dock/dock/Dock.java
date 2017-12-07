package com.yarachkin.dock.dock;

import com.yarachkin.dock.entity.Ship;

public class Dock {
    private int capacity;
    private int dockContainersCounts;
    private int pierCounts;

    private Dock() {
    }

    private static class SingletonHolder {
        private static final Dock INSTANCE = new Dock();
    }

    public static Dock getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void initializeCapacityAndDockContainersCounts(int capacity, int dockContainersCounts, int pierCounts) {
        this.capacity = capacity;
        this.dockContainersCounts = dockContainersCounts;
        this.pierCounts = pierCounts;
    }

    void loadOrUnloadContainers(Ship ship) {
        int loadingAvailableContainers = capacity - dockContainersCounts;
        int unloadedFromShipContainers = ship.unloadFromShipContainers(loadingAvailableContainers);
        dockContainersCounts += unloadedFromShipContainers;

        int loadingInShipContainers = ship.loadInShipContainers(dockContainersCounts);
        dockContainersCounts -= loadingInShipContainers;
    }

    int getCapacity() {
        return capacity;
    }

    void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    int getDockContainersCounts() {
        return dockContainersCounts;
    }

    void setDockContainersCounts(int dockContainersCounts) {
        this.dockContainersCounts = dockContainersCounts;
    }
}
