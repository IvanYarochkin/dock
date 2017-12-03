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
