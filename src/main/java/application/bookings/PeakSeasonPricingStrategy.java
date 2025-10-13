package application.bookings;

/**
 * Applies a 35% surcharge used during the busy season.
 */
public class PeakSeasonPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(double baseNightlyRate, int nights) {
        double base = baseNightlyRate * nights;
        return base * 1.35;
    }
}
