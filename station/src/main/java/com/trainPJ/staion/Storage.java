package com.trainPJ.staion;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component

public class Storage {
    private List<Station> Stations = new ArrayList<>();

    public void addStation(Station Station) {
        Stations.add(Station);
    }

    public List<Station> getStations() {
        return Stations;
    }
    public Station findStationById(String id) {
        for (Station Station : Stations) {
            if (Station.getId().equals(id)) {
                return Station;
            }
        }
        return null;
    }
    public Station findStationByName(String name) {
        for (Station Station : Stations) {
            if (Station.getname().equals(name)) {
                return Station;
            }
        }
        return null;
    }

    public boolean updateStation(String id, Station updatedStation) {
        for (int i = 0; i < Stations.size(); i++) {
            if (Stations.get(i).getId().equals(id)) {
                Stations.set(i, updatedStation);
                return true;
            }
        }
        return false;
    }


}
