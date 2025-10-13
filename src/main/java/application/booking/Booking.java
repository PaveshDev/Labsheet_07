package application.booking;

import application.room.Room;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a reservation for a specific room.
 */
public class Booking {
    private final String bookingId;
    private final Room room;
    private final String customerId;
    private final LocalDate checkInDate;
    private final int nights;
    private final double totalCost;

    public Booking(String bookingId, Room room, String customerId, LocalDate checkInDate, int nights, double totalCost) {
        this.bookingId = Objects.requireNonNull(bookingId, "bookingId");
        this.room = Objects.requireNonNull(room, "room");
        this.customerId = Objects.requireNonNull(customerId, "customerId");
        this.checkInDate = Objects.requireNonNull(checkInDate, "checkInDate");
        if (nights <= 0) {
            throw new IllegalArgumentException("nights must be positive");
        }
        this.nights = nights;
        this.totalCost = totalCost;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Room getRoom() {
        return room;
    }

    public String getCustomerId() {
        return customerId;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public int getNights() {
        return nights;
    }

    public double getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", room=" + room.getRoomNumber() +
                ", customerId='" + customerId + '\'' +
                ", checkInDate=" + checkInDate +
                ", nights=" + nights +
                ", totalCost=" + totalCost +
                '}';
    }
}
