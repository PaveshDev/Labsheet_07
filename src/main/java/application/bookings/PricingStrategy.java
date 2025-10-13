package application.bookings;

/**
 * Strategy for calculating the final price of a booking.
 */
public interface PricingStrategy {
    double calculatePrice(double baseNightlyRate, int nights);
}
