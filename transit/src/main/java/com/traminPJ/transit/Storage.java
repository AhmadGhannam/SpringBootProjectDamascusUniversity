package com.traminPJ.transit;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component

public class Storage {
    private List<Transit> transits = new ArrayList<>();

    public void addTransit(Transit transit) {
        transits.add(transit);
    }

    public List<Transit> getTransits() {
        return transits;
    }
    public Transit findTransitById(String id) {
        for (Transit transit : transits) {
            if (transit.getId().equals(id)) {
                return transit;
            }
        }
        return null;
    }

    public boolean updateTransit(String id, Transit updatedTransit) {
        for (int i = 0; i < transits.size(); i++) {
            if (transits.get(i).getId().equals(id)) {
                transits.set(i, updatedTransit);
                return true;
            }
        }
        return false;
    }


}
