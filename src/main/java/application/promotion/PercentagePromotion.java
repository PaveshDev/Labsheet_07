package application.promotion;

/**
 * Applies a percentage discount to the provided amount.
 */
public class PercentagePromotion implements Promotion {
    private final String name;
    private final double percentage;

    public PercentagePromotion(String name, double percentage) {
        this.name = name;
        this.percentage = percentage;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public double apply(double amount) {
        return amount - (amount * percentage);
    }
}
