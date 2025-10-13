package application.bookings;

/**
 * Default pricing strategy with no adjustments.
 */
public class StandardPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(double baseNightlyRate, int nights) {
        return baseNightlyRate * nights;
    }
}
