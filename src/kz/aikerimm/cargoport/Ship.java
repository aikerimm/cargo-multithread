package kz.aikerimm.cargoport;

public class Ship {
    public static final int MAX_LOAD = 100;

    private int currentLoad;
    private int id;

    public Ship(int load, int id) {
        currentLoad = load;
        this.id = id;
    }

    @Override
    public String toString() {
        return "kz.aikerimm.cargoport.Ship " + id + " (load=" + currentLoad + ")";
    }

    public int getCurrentLoad() {
        return currentLoad;
    }

    public void setCurrentLoad(int currentLoad) {
        this.currentLoad = currentLoad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
