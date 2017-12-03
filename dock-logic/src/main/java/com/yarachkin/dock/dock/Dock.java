package com.yarachkin.dock.dock;

public class Dock {
    private int capacity;
    private int dockContainersCounts;

    private Dock() {
    }

    private static class SingletonHolder {
        private static final Dock INSTANCE = new Dock();
    }

    public static Dock getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getDockContainersCounts() {
        return dockContainersCounts;
    }

    public void setDockContainersCounts(int dockContainersCounts) {
        this.dockContainersCounts = dockContainersCounts;
    }
}
