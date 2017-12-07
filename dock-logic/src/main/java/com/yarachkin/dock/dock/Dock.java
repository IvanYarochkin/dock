package com.yarachkin.dock.dock;

import com.yarachkin.dock.entity.Ship;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Dock {
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

    public synchronized void loadOrUnloadContainers(Ship ship) {
        Lock lock = new ReentrantLock();

        lock.lock();
        int loadingAvailableContainers = capacity - dockContainersCounts;
        int unloadedFromShipContainers = ship.unloadFromShipContainers(loadingAvailableContainers);
        dockContainersCounts += unloadedFromShipContainers;

        int loadingInShipContainers = ship.loadInShipContainers(dockContainersCounts);
        dockContainersCounts -= loadingInShipContainers;

        lock.unlock();
    }

    int getDockContainersCounts() {
        return dockContainersCounts;
    }
}
