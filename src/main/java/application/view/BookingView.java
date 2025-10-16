package application.view;

import application.model.Booking;

import java.util.List;

public class BookingView {
    public void showBookingCreated(Booking booking) {
        System.out.println("[View] Booking confirmed: " + booking);
    }

    public void showBookings(List<Booking> bookings) {
        System.out.println("[View] Current bookings:");
        bookings.forEach(booking -> System.out.println("  - " + booking));
    }
}
