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
}
