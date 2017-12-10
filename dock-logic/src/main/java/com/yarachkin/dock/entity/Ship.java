package com.yarachkin.dock.entity;

import com.yarachkin.dock.dock.Dock;
import com.yarachkin.dock.dock.Pier;
import com.yarachkin.dock.exception.LogicDockException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ship extends Thread {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private long shipId;
    private int capacity;
    private int boardContainersCounts;
    private int unloadingContainersCounts;
    private int loadingContainersCounts;

    public Ship(long shipId, int capacity, int boardContainersCounts, int unloadingContainersCounts, int loadingContainersCounts) {
        super();
        this.shipId = shipId;
        this.capacity = capacity;
        this.boardContainersCounts = boardContainersCounts;
        this.unloadingContainersCounts = unloadingContainersCounts;
        this.loadingContainersCounts = loadingContainersCounts;
    }

    public int loadInShipContainers(int containersCounts) {
        int loadedContainers;
        if ( capacity - boardContainersCounts < containersCounts &&
                capacity - boardContainersCounts < loadingContainersCounts) {
            loadedContainers = capacity - boardContainersCounts;
            boardContainersCounts = capacity;
            loadingContainersCounts -= loadedContainers;
        } else if ( capacity - boardContainersCounts < containersCounts || containersCounts > loadingContainersCounts ) {
            boardContainersCounts += loadingContainersCounts;
            loadedContainers = loadingContainersCounts;
            loadingContainersCounts = 0;
        } else {
            boardContainersCounts += containersCounts;
            loadingContainersCounts -= containersCounts;
            loadedContainers = containersCounts;
        }
        return loadedContainers;
    }

    public int unloadFromShipContainers(int containersCounts) {
        int unloadedContainers;
        if ( boardContainersCounts < containersCounts || containersCounts > unloadingContainersCounts ) {
            boardContainersCounts -= unloadingContainersCounts;
            unloadedContainers = unloadingContainersCounts;
            unloadingContainersCounts = 0;
        } else {
            boardContainersCounts -= containersCounts;
            unloadingContainersCounts -= containersCounts;
            unloadedContainers = containersCounts;
        }
        return unloadedContainers;
    }

    @Override
    public void run() {
        try {
            Pier pier = Dock.getInstance().acquirePier();
            pier.loadOrUnloadContainers(this);
            pier.close();
        } catch (LogicDockException e) {
            LOGGER.log(Level.INFO, e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( !(o instanceof Ship) ) return false;

        Ship ship = (Ship) o;

        if ( shipId != ship.shipId ) return false;
        if ( capacity != ship.capacity ) return false;
        if ( boardContainersCounts != ship.boardContainersCounts ) return false;
        if ( loadingContainersCounts != ship.loadingContainersCounts ) return false;
        return unloadingContainersCounts == ship.unloadingContainersCounts;
    }

    @Override
    public int hashCode() {
        int result = (int) (shipId ^ (shipId >>> 32));
        result = 31 * result + capacity;
        result = 31 * result + boardContainersCounts;
        result = 31 * result + loadingContainersCounts;
        result = 31 * result + unloadingContainersCounts;
        return result;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "shipId=" + shipId +
                ", capacity=" + capacity +
                ", boardContainersCounts=" + boardContainersCounts +
                ", loadingContainersCounts=" + loadingContainersCounts +
                ", unloadingContainersCounts=" + unloadingContainersCounts +
                '}';
    }
}
