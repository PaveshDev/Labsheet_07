package application.booking.strategy;

import application.booking.Booking;
import application.room.Room;

import java.time.LocalDate;

/**
 * Strategy abstraction for calculating booking costs.
 */
public interface PricingStrategy {
    double calculateTotal(Room room, String customerId, LocalDate checkInDate, int nights);

    default Booking createBooking(String bookingId, Room room, String customerId, LocalDate checkInDate, int nights) {
        double total = calculateTotal(room, customerId, checkInDate, nights);
        return new Booking(bookingId, room, customerId, checkInDate, nights, total);
    }
}
