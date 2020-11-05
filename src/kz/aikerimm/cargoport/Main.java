package kz.aikerimm.cargoport;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        List<Dock> docks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            docks.add(new Dock(random.nextInt(Dock.MAX_LOAD + 1), i));
        }
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        try {
            for (int i = 0; i < 50; i++) {
                int finalI = i;
                executorService.execute(new Thread(() -> {
                    Ship ship = new Ship(random.nextInt(Ship.MAX_LOAD + 1), finalI);
                    docks.stream().filter(dock -> !dock.lock.isLocked()).findFirst().ifPresent(dock -> dock.acceptShip(ship));
                }));
            }
        } finally {
            executorService.shutdown();
        }
    }
}
