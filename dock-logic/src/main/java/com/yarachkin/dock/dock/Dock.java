package com.yarachkin.dock.dock;

import com.yarachkin.dock.entity.Ship;
import com.yarachkin.dock.exception.LogicDockException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Dock {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);
    private static ReentrantLock instanceLock = new ReentrantLock();
    private static Dock instance;

    private int capacity;
    private int dockContainersCounts;
    private ReentrantLock loadOrUnloadLock;
    private ReentrantLock pierActionLock;
    private Condition pierActionCondition;
    private ArrayDeque<Pier> freePiers;
    private ArrayDeque<Pier> busyPiers;

    private Dock() {
        freePiers = new ArrayDeque<>();
        busyPiers = new ArrayDeque<>();
        loadOrUnloadLock = new ReentrantLock();
        pierActionLock = new ReentrantLock();
        pierActionCondition = pierActionLock.newCondition();
    }

    public static Dock getInstance() {
        if ( !instanceCreated.get() ) {
            instanceLock.lock();
            try {
                if ( !instanceCreated.get() ) {
                    instance = new Dock();
                    instanceCreated.set(true);
                }
            } finally {
                instanceLock.unlock();
            }
        }
        return instance;
    }

    public void initializeCapacityAndDockContainersCounts(int capacity, int dockContainersCounts, int pierCounts) {
        this.capacity = capacity;
        this.dockContainersCounts = dockContainersCounts;
        freePiers = new ArrayDeque<>();
        busyPiers = new ArrayDeque<>();
        if ( freePiers.isEmpty() && busyPiers.isEmpty() ) {
            for (int i = 0; i < pierCounts; i++) {
                freePiers.push(new Pier());
            }
        }
    }

    public Pier acquirePier() throws LogicDockException {
        pierActionLock.lock();
        try {
            if ( !freePiers.isEmpty() || !busyPiers.isEmpty() ) {
                if ( freePiers.isEmpty() ) {
                    pierActionCondition.await();
                }
                Pier pier = freePiers.poll();
                busyPiers.push(pier);
                LOGGER.log(Level.DEBUG, Thread.currentThread() + " acquire a pier.");
                return pier;
            } else {
                throw new LogicDockException("Pier size = 0.");
            }
        } catch (InterruptedException e) {
            throw new LogicDockException(e);
        } finally {
            pierActionLock.unlock();
        }
    }

    boolean putBackPier(Pier pier) throws LogicDockException {
        if ( pier != null ) {
            try {
                pierActionLock.lock();
                if ( busyPiers.remove(pier) ) {
                    freePiers.push(pier);
                    if ( freePiers.size() == 1 ) {
                        pierActionCondition.signal();
                    }
                    LOGGER.log(Level.DEBUG, Thread.currentThread() + " put back a pier.");
                    return true;
                } else {
                    throw new LogicDockException("Pier isn't in the busyPiers.");
                }
            } finally {
                pierActionLock.unlock();
            }
        } else {
            throw new LogicDockException("Can't add null pier.");
        }
    }

    void loadOrUnloadContainers(Ship ship) throws LogicDockException {
        loadOrUnloadLock.lock();
        try {
            LOGGER.log(Level.DEBUG, ship + " load or unload containers.");
            int loadingAvailableContainers = capacity - dockContainersCounts;
            int unloadedFromShipContainers = ship.unloadFromShipContainers(loadingAvailableContainers);
            dockContainersCounts += unloadedFromShipContainers;
            TimeUnit.SECONDS.sleep((long) (0.3 * unloadedFromShipContainers));
            LOGGER.log(Level.DEBUG, ship + " load " + unloadedFromShipContainers + " containers.");

            int loadingInShipContainers = ship.loadInShipContainers(dockContainersCounts);
            dockContainersCounts -= loadingInShipContainers;
            TimeUnit.SECONDS.sleep((long) (0.3 * loadingInShipContainers));
            LOGGER.log(Level.DEBUG, ship + " unload " + loadingInShipContainers + " containers.\n");
        } catch (InterruptedException e) {
            throw new LogicDockException(e);
        } finally {
            loadOrUnloadLock.unlock();
        }
    }

    int getDockContainersCounts() {
        return dockContainersCounts;
    }

    int getFreePiersSize() {
        return freePiers.size();
    }

    int getBusyPiersSize() {
        return busyPiers.size();
    }
}
