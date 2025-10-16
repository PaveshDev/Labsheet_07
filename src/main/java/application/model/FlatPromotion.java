package application.model;

/**
 * Applies a flat discount amount to the price.
 */
public class FlatPromotion extends Promotion {
    private final double discountAmount;

    public FlatPromotion(String name, double discountAmount) {
        super(name);
        this.discountAmount = discountAmount;
    }

    @Override
    public double apply(double basePrice) {
        return Math.max(0, basePrice - discountAmount);
    }
}
