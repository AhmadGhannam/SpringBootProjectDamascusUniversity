package com.traminPJ.transit;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class transitService {



    private final RestTemplate rest;

    private CircuitBreakerFactory circuitBreakerFactory = null;
    public Storage storage;

    @Autowired
    public transitService(RestTemplateBuilder builder, CircuitBreakerFactory circuitBreakerFactory, Storage storage){
        this.rest = builder.build();
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.storage = storage;


    }

    public String alter(Throwable t)
    {
        System.out.println("Fallback executed: " + t.getMessage());
        return "Service Station is Down" ;
    }



    @CircuitBreaker(name = "checkStationServiceStatus", fallbackMethod = "alter")
    public  String FallbackTest()

    {
//        return rest.getForObject("http://TAC/tac/what-is-your-name",String.class);

        return circuitBreakerFactory.create("checkStationServiceStatus").run(() -> rest.getForObject("http://STATION/station/getStationid/Station_A", String.class), throwable -> alter(throwable));

    }

//
//    }



    public String addTransit(@RequestBody Transit transit) {
        storage.addTransit(transit);
        return " added succefuly ";
    }


    public List<Transit> getTransits() {

        return storage.getTransits();
    }

    public String  addRandomTransit() {
//        for(int i =0 ; i<10 ; i++){
//            Transit randomTransit = TransitUtils.createRandomTransit();
//            storage.addTransit(randomTransit);
//        }

        Transit t1 = new Transit("1", "Available", "Station_A", "Station_B", 15000);
        Transit t2 = new Transit("2", "Unavailable", "Station_B", "Station_C", 20000);
        Transit t3 = new Transit("3", "Available", "Station_C", "Station_D", 12500);
        Transit t4 = new Transit("4", "Available", "Station_A", "Station_D", 16000);
        Transit t5 = new Transit("5", "Available", "Station_A", "Station_A", 25000);
        storage.addTransit(t1);
        storage.addTransit(t2);
        storage.addTransit(t3);
        storage.addTransit(t4);
        storage.addTransit(t5);
        return "added 10 transit to the list";
    }


    public String editTransit(@PathVariable String id, @RequestBody Transit updatedTransit) {
        boolean updated = storage.updateTransit(id, updatedTransit);
        if (updated) {
            return "Transit with ID " + id + " updated successfully";
        } else {
            return "Transit with ID " + id + " not found";
        }
    }


    public String checkTransit(@PathVariable String id) {
        String status="" ;
        Transit transit = storage.findTransitById(id);
        if (transit != null) {
            String transitStatus =  transit.getstatus();
            if (transitStatus.equals("Available")){
                String originStationid = circuitBreakerFactory.create("checkStationServiceStatus").run(() -> rest.getForObject("http://localhost:4443/station/getStationid/"+ transit.getOrigin(), String.class), throwable -> alter(throwable));
                        //rest.getForObject("http://STATION/station/getStationid/"+ transit.getOrigin(), String.class);


                String destinationStationid =circuitBreakerFactory.create("checkStationServiceStatus").run(() -> rest.getForObject("http://localhost:4443/station/getStationid/"+ transit.getDestination(), String.class), throwable -> alter(throwable));


                        //rest.getForObject("http://STATION/station/getStationid/" + transit.getDestination(), String.class);




                ///res4j response handle
                if ( originStationid.equals("Service Station is Down") || destinationStationid.equals("Service Station is Down")) {
                    return "Service Station is Down.......  please wait";
                }
                /// normal response handle
                if ( originStationid.equals("Unavailable") || destinationStationid.equals("Unavailable") ||originStationid.equals("Station not found") || destinationStationid.equals("Station not found")  ) {
                    return "Station not found";
                }


                String originStation = circuitBreakerFactory.create("checkStationServiceStatus").run(() -> rest.getForObject("http://localhost:4443/station/getStationid/"+  originStationid, String.class), throwable -> alter(throwable));

                        //rest.getForObject("http://STATION/station/checkStation/"+ originStationid, String.class);
                String destinationStation = circuitBreakerFactory.create("checkStationServiceStatus").run(() -> rest.getForObject("http://localhost:4443/station/getStationid/"+  destinationStationid, String.class), throwable -> alter(throwable));

                        //rest.getForObject("http://STATION/station/checkStation/" + destinationStationid, String.class);


                ///res4j response handle
                if ( originStationid.equals("Service Station is Down") || destinationStationid.equals("Service Station is Down")) {
                    return "Service Station is Down.......  please wait";
                }
                /// normal response handle
                if ( originStation.equals("Unavailable") || destinationStation.equals("Unavailable") ) {
                    return "station Unavailable";

                }
                status =  "Available";
            }
            status= transit.getstatus();



        } else {
            status= "transit not found";
        }
        return status;




}}

