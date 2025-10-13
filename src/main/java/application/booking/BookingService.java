package application.booking;

import application.booking.strategy.PricingStrategy;
import application.room.Room;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Uses the Strategy pattern to support multiple pricing calculations when
 * creating bookings.
 */
public class BookingService {
    private final Map<String, PricingStrategy> strategies = new LinkedHashMap<>();
    private final Map<String, Booking> bookings = new LinkedHashMap<>();
    private int nextId = 1;

    public void registerStrategy(String name, PricingStrategy strategy) {
        strategies.put(name, Objects.requireNonNull(strategy, "strategy"));
    }

    public Booking createBooking(String strategyName, Room room, String customerId, LocalDate checkInDate, int nights) {
        PricingStrategy strategy = strategies.get(strategyName);
        if (strategy == null) {
            throw new IllegalArgumentException("Unknown pricing strategy: " + strategyName);
        }
        String bookingId = String.format("B%03d", nextId++);
        Booking booking = strategy.createBooking(bookingId, room, customerId, checkInDate, nights);
        bookings.put(bookingId, booking);
        return booking;
    }

    public Booking findBooking(String bookingId) {
        return bookings.get(bookingId);
    }
}
