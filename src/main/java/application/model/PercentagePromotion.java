package application.model;

/**
 * Applies a percentage discount to the price.
 */
public class PercentagePromotion extends Promotion {
    private final double percentage;

    public PercentagePromotion(String name, double percentage) {
        super(name);
        this.percentage = percentage;
    }

    @Override
    public double apply(double basePrice) {
        return basePrice * (1 - percentage);
    }
}
