package kz.aikerimm.cargoport;

import java.util.concurrent.locks.ReentrantLock;

public class Dock {
    static int MAX_LOAD = 200;
    int load;
    int index;
    ReentrantLock lock = new ReentrantLock();

    public Dock(int load, int index) {
        this.load = load;
        this.index = index;
    }

    public void acceptShip(Ship ship) {
        lock.lock();
        try {
            receiveLoad(ship);
            giveLoad(ship);
        } finally {
            lock.unlock();
        }
    }

    public void receiveLoad(Ship ship) {
        System.out.println(Thread.currentThread().getName() + ": " + this + " is going to receive load from " + ship);
        int shipLoad = ship.getCurrentLoad();
        int receivedLoad;
        if (load + ship.getCurrentLoad() < MAX_LOAD) {
            load += shipLoad;
            ship.setCurrentLoad(0);
            receivedLoad = shipLoad;
        } else {
            int loadToReceive = MAX_LOAD - load;
            load = MAX_LOAD;
            ship.setCurrentLoad(ship.getCurrentLoad() - loadToReceive);
            receivedLoad = loadToReceive;
        }
        System.out.println(Thread.currentThread().getName() + ": " + this + " has received load " + receivedLoad + " from " + ship);
    }

    /**
     * gives load to ship
     *
     * @param ship
     */
    public void giveLoad(Ship ship) {
        System.out.println(Thread.currentThread().getName() + ": " + this + " is going to give load to " + ship);
        int canGive = Ship.MAX_LOAD - ship.getCurrentLoad();
        if (load < canGive) {
            canGive = load;
        }
        ship.setCurrentLoad(ship.getCurrentLoad() + canGive);
        load -= canGive;
        System.out.println(Thread.currentThread().getName() + ": " + this + " has given load " + canGive + " to " + ship);
    }

    @Override
    public String toString() {
        return "kz.aikerimm.cargoport.Dock " + index + " (load=" + load + ")";
    }
}
