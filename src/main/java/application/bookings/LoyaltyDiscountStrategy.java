package application.bookings;

/**
 * Applies a flat discount to reward loyalty members.
 */
public class LoyaltyDiscountStrategy implements PricingStrategy {
    private final double discountRate;

    public LoyaltyDiscountStrategy(double discountRate) {
        if (discountRate < 0 || discountRate > 1) {
            throw new IllegalArgumentException("Discount rate must be between 0 and 1");
        }
        this.discountRate = discountRate;
    }

    @Override
    public double calculatePrice(double baseNightlyRate, int nights) {
        double base = baseNightlyRate * nights;
        return base * (1 - discountRate);
    }
}
