package application.bookings;

import application.rooms.Room;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Manages bookings and delegates pricing to a pluggable {@link PricingStrategy}.
 */
public class BookingService {
    private final List<BookingSummary> bookings = new ArrayList<>();
    private PricingStrategy pricingStrategy = new StandardPricingStrategy();

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = Objects.requireNonNull(pricingStrategy, "Pricing strategy must not be null");
    }

    public BookingSummary createBooking(String guestName, Room room, LocalDate checkIn, int nights, double nightlyRate) {
        if (guestName == null || guestName.isBlank()) {
            throw new IllegalArgumentException("Guest name must not be blank");
        }
        Objects.requireNonNull(room, "Room must not be null");
        Objects.requireNonNull(checkIn, "Check-in date must not be null");
        if (nights <= 0) {
            throw new IllegalArgumentException("Nights must be positive");
        }
        if (nightlyRate <= 0) {
            throw new IllegalArgumentException("Nightly rate must be positive");
        }

        double totalPrice = pricingStrategy.calculatePrice(nightlyRate, nights);
        BookingSummary booking = new BookingSummary(guestName, room, checkIn, nights, totalPrice);
        bookings.add(booking);
        return booking;
    }

    public List<BookingSummary> listBookings() {
        return List.copyOf(bookings);
    }

    /**
     * Immutable summary of a booking.
     */
    public record BookingSummary(String guestName, Room room, LocalDate checkInDate, int nights, double totalPrice) {
    }
}
