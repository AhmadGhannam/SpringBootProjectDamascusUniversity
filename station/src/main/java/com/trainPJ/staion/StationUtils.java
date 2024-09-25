package com.trainPJ.staion;

import java.util.Random;
import java.util.UUID;

public class StationUtils {
    private static final Random RANDOM = new Random();

//    public static Station createRandomTransit() {
//        Station Station = new Station();
//        Station.setId(UUID.randomUUID().toString());
//        Station.setstatus(getRandomStatus());
//        Station.setname(getRandomStationName());
//        return Station;
//    }

    private static String getRandomStatus() {
        String[] types = {"Available", "Unavailable", "maintenance"};
        return types[RANDOM.nextInt(types.length)];
    }

    private static String getRandomStationName() {
        String[] stations = {"Station A", "Station B", "Station C", "Station D"};
        return stations[RANDOM.nextInt(stations.length)];
    }


}
