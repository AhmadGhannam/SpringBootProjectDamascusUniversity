package com.trainPJ.staion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/station")

public class StationController {

    public Storage storage;

    @Autowired
    public StationController(Storage storage) {
        this.storage = storage;
    }

    @PostMapping("/addstation")
    public String addStation(@RequestBody Station Station) {
        storage.addStation(Station);
        return " added succefuly ";
    }

    @GetMapping("/getstation")
    public List<Station> getTransits() {

        return storage.getStations();
    }

    @GetMapping("/addrandomtransit")
    public String  addRandomTransit() {
//        for(int i =0 ; i<10 ; i++){
//            Station randomTransit = StationUtils.createRandomTransit();
//            storage.addStation(randomTransit);
//        }
        Station s1 =  new Station("1","Unavailable","Station_A");
        Station s2 =  new Station("2","Available","Station_B");
        Station s3 =  new Station("3","Available","Station_C");
        Station s4 =  new Station("4","Available","Station_D");
        storage.addStation(s1);
        storage.addStation(s2);
        storage.addStation(s3);
        storage.addStation(s4);
        return "added 10 transit to the list";
    }

    @GetMapping("/checkStation/{id}")
    public String checkStation(@PathVariable String id) {
        String status="";
        Station Station = storage.findStationById(id);
        if (Station != null) {
               status = Station.getstatus();

        } else {
            status= "Station not found";
        }
        return  status;
    }
    @GetMapping("/getStationid/{name}")
    public String getStationId(@PathVariable String name  ) {
        String status="";
        Station Station = storage.findStationByName(name);
        if (Station != null) {
            status = Station.getId();

        } else {
            status= "Station not found";
        }
        return  status;
    }

    @PutMapping("/editStation/{id}")
    public String editStation(@PathVariable String id, @RequestBody Station updatedStation) {
        boolean updated = storage.updateStation(id, updatedStation);
        if (updated) {
            return "Station with ID " + id + " updated successfully";
        } else {
            return "Station with ID " + id + " not found";
        }
    }
}
