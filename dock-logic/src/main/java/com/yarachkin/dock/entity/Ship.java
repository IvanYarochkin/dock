package com.yarachkin.dock.entity;

public class Ship extends Thread {
    private long shipId;
    private int capacity;
    private int boardContainersCounts;
    private int loadingContainersCounts;
    private int uploadingContainersCounts;

    public long getShipId() {
        return shipId;
    }

    public void setShipId(long shipId) {
        this.shipId = shipId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getBoardContainersCounts() {
        return boardContainersCounts;
    }

    public void setBoardContainersCounts(int boardContainersCounts) {
        this.boardContainersCounts = boardContainersCounts;
    }

    public int getUploadingContainersCounts() {
        return uploadingContainersCounts;
    }

    public void setUploadingContainersCounts(int uploadingContainersCounts) {
        this.uploadingContainersCounts = uploadingContainersCounts;
    }

    public int getLoadingContainersCounts() {
        return loadingContainersCounts;
    }

    public void setLoadingContainersCounts(int loadingContainersCounts) {
        this.loadingContainersCounts = loadingContainersCounts;
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
        return uploadingContainersCounts == ship.uploadingContainersCounts;
    }

    @Override
    public int hashCode() {
        int result = (int) (shipId ^ (shipId >>> 32));
        result = 31 * result + capacity;
        result = 31 * result + boardContainersCounts;
        result = 31 * result + loadingContainersCounts;
        result = 31 * result + uploadingContainersCounts;
        return result;
    }
}
