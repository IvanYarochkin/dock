package com.yarachkin.dock.dock;

import com.yarachkin.dock.entity.Ship;

import java.util.ArrayDeque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Dock {
    private int capacity;
    private int dockContainersCounts;
    private Lock loadOrUnloadLock;
    private ArrayDeque<Pier> freePiers;
    private ArrayDeque<Pier> busyPiers;

    private Dock() {
        freePiers = new ArrayDeque<>();
        busyPiers = new ArrayDeque<>();
        loadOrUnloadLock = new ReentrantLock();
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
        if ( freePiers.isEmpty() && busyPiers.isEmpty() ) {
            for (int i = 0; i < pierCounts; i++) {
                freePiers.push(new Pier());
            }
        }
    }

    public void loadOrUnloadContainers(Ship ship) {

        loadOrUnloadLock.lock();
        int loadingAvailableContainers = capacity - dockContainersCounts;
        int unloadedFromShipContainers = ship.unloadFromShipContainers(loadingAvailableContainers);
        dockContainersCounts += unloadedFromShipContainers;

        int loadingInShipContainers = ship.loadInShipContainers(dockContainersCounts);
        dockContainersCounts -= loadingInShipContainers;
        loadOrUnloadLock.unlock();
    }

    int getDockContainersCounts() {
        return dockContainersCounts;
    }
}
