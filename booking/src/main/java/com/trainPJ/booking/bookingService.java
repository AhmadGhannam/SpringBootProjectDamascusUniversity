package com.trainPJ.booking;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class bookingService {


    private final RestTemplate rest;

    private final BookingStorage storage;

    private CircuitBreakerFactory circuitBreakerFactory = null;
    @Autowired
    public bookingService(RestTemplateBuilder builder, CircuitBreakerFactory circuitBreakerFactory, BookingStorage storage){
        this.rest = builder.build();
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.storage = storage;
    }

    public String alter(Throwable t)
    {
        System.out.println("Fallback executed: " + t.getMessage());
        return "Service transit is Down" ;
    }

    @CircuitBreaker(name = "checkTransitServiceStatus", fallbackMethod = "alter")
    public  String FallbackTest()

    {
//        return rest.getForObject("http://TAC/tac/what-is-your-name",String.class);

        return circuitBreakerFactory.create("checkTransitServiceStatus").run(() -> rest.getForObject("http://TRANSIT/transit/checktransit/1", String.class), throwable -> alter(throwable));

    }


    @CircuitBreaker(name = "checkTransitServiceStatus", fallbackMethod = "alter")
    public String addBooking(@RequestBody Booking booking) {

        if (booking.getOriginStationId() == null || booking.getDestinationStationId() == null) {
            return "Invalid station details.";
        }
        if (booking.getTransitId() == null) {
            return "Invalid transit details.";
        }



        String transitStatus = circuitBreakerFactory.create("checkTransitServiceStatus").run(() -> rest.getForObject("http://localhost:4445/transit/checktransit/"+ booking.getTransitId(), String.class), throwable -> alter(throwable));


                //rest.getForObject("http://TRANSIT/transit/checktransit/"+ booking.getTransitId(), String.class);
        if ( transitStatus.equals("Service transit is Down") ) {
            return "Service transit is Down ..... please wait ";
        }

        if ( transitStatus.equals("Service Station is Down.......  please wait") ) {
            return "Service Station is Down.......  please wait";
        }
        if ( transitStatus.equals("Unavailable") ) {
            return "transit  Unavailable";
        }else
        if ( transitStatus.equals("station Unavailable") ) {
            return "station Unavailable";
        }

        booking.setId(UUID.randomUUID().toString());
        storage.addBooking(booking);
        return "Booking added successfully with ID: " + booking.getId();
        //return rest.getForObject("http://127.0.0.1:4443/station/checkStation/" + booking.getOriginStationId().toString(), String.class);
    }

    public List<Booking> getBookings() {
        return storage.getBookings();
    }

    public Booking getBookingById(@PathVariable String id) {
        return storage.findBookingById(id);
    }


    public String updateBooking(@PathVariable String id, @RequestBody Booking updatedBooking) {
        boolean updated = storage.updateBooking(id, updatedBooking);
        if (updated) {
            return "Booking with ID " + id + " updated successfully";
        } else {
            return "Booking with ID " + id + " not found";
        }
    }

    public String deleteBooking(@PathVariable String id) {
        boolean deleted = storage.deleteBooking(id);
        if (deleted) {
            return "Booking with ID " + id + " deleted successfully";
        } else {
            return "Booking with ID " + id + " not found";
        }
    }

    public String checkBookingStatus(@PathVariable String id) {
        Booking booking = storage.findBookingById(id);
        if (booking != null) {
            return "Status of booking with ID " + id + " is: " + booking.getStatus();
        } else {
            return "Booking with ID " + id + " not found";
        }
    }
}
