package com.yarachkin.dock.entity;

public class Ship extends Thread {
    private long shipId;
    private int capacity;
    private int boardContainersCounts;
    private int loadingContainersCounts;
    private int unloadingContainersCounts;

    public void setShipId(long shipId) {
        this.shipId = shipId;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setBoardContainersCounts(int boardContainersCounts) {
        this.boardContainersCounts = boardContainersCounts;
    }

    public void setUnloadingContainersCounts(int unloadingContainersCounts) {
        this.unloadingContainersCounts = unloadingContainersCounts;
    }

    public void setLoadingContainersCounts(int loadingContainersCounts) {
        this.loadingContainersCounts = loadingContainersCounts;
    }

    public int loadInShipContainers(int containersCounts) {
        int loadedContainers;
        if ( capacity - boardContainersCounts < containersCounts || containersCounts > loadingContainersCounts ) {
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
