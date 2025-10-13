package application.booking.strategy;

import application.room.Room;

import java.time.LocalDate;

/**
 * Default strategy that multiplies the nightly rate by the requested nights.
 */
public class StandardPricingStrategy implements PricingStrategy {
    @Override
    public double calculateTotal(Room room, String customerId, LocalDate checkInDate, int nights) {
        return room.getNightlyRate() * nights;
    }
}
