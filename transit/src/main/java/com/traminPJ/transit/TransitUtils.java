package com.traminPJ.transit;

import java.util.Random;
import java.util.UUID;

public class TransitUtils {
    private static final Random RANDOM = new Random();

//    public static Transit createRandomTransit() {
////        Transit transit = new Transit();
////        transit.setId(UUID.randomUUID().toString());
////        transit.setstatus(getRandomStatus());
////        transit.setOrigin(getRandomStation());
////        transit.setDestination(getRandomStation());
////        transit.setCost(1 + (99 * RANDOM.nextDouble()));  // Cost between 1.0 and 100.0
//        return transit;
//    }

    private static String getRandomStatus() {
        String[] types = {"Available", "Unavailable", "maintenance"};
        return types[RANDOM.nextInt(types.length)];
    }

    private static String getRandomStation() {
        String[] stations = {"Station A", "Station B", "Station C", "Station D"};
        return stations[RANDOM.nextInt(stations.length)];
    }


}
