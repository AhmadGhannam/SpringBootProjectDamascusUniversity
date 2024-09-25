package com.trainPJ.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    RestTemplate rest;


    private final BookingStorage storage;
    @Autowired
    bookingService Service;

    @Autowired
    public BookingController(BookingStorage storage) {
        this.storage = storage;
    }

    @PostMapping("addbooking")
    public String addBooking(@RequestBody Booking booking) {
        return Service.addBooking(booking);
    }

    @GetMapping("getbooking")
    public List<Booking> getBookings() {
        return Service.getBookings();
    }

    @GetMapping("getbooking/{id}")
    public Booking getBookingById(@PathVariable String id) {
        return Service.getBookingById(id);
    }

    @PutMapping("updateBooking/{id}")
    public String updateBooking(@PathVariable String id, @RequestBody Booking updatedBooking) {
       return Service.updateBooking(id,updatedBooking);
    }

    @DeleteMapping("deleteBooking/{id}")
    public String deleteBooking(@PathVariable String id) {
       return Service.deleteBooking(id);
    }

    @GetMapping("checkBookingStatus/{id}/status")
    public String checkBookingStatus(@PathVariable String id) {
       return Service.checkBookingStatus(id);
    }


    @GetMapping("/fallback")
    public String fallbacktest() {

        return Service.FallbackTest();
    }
}
