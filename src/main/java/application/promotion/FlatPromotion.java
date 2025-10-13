package application.promotion;

/**
 * Applies a flat discount.
 */
public class FlatPromotion implements Promotion {
    private final String name;
    private final double discountAmount;

    public FlatPromotion(String name, double discountAmount) {
        this.name = name;
        this.discountAmount = discountAmount;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public double apply(double amount) {
        return Math.max(0, amount - discountAmount);
    }
}
