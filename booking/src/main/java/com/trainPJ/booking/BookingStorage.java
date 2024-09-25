package com.trainPJ.booking;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookingStorage {
    private List<Booking> bookings = new ArrayList<>();

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public Booking findBookingById(String id) {
        for (Booking booking : bookings) {
            if (booking.getId().equals(id)) {
                return booking;
            }
        }
        return null;
    }

    public boolean updateBooking(String id, Booking updatedBooking) {
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getId().equals(id)) {
                bookings.set(i, updatedBooking);
                return true;
            }
        }
        return false;
    }

    public boolean deleteBooking(String id) {
        return bookings.removeIf(booking -> booking.getId().equals(id));
    }
}
