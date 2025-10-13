package application.promotion;

import java.util.Locale;

/**
 * Demonstrates the Factory Method pattern by creating promotions based on a
 * textual type.
 */
public class PromotionFactory {
    public Promotion createPromotion(String type, String name, double value) {
        String normalized = type.toLowerCase(Locale.ROOT);
        return switch (normalized) {
            case "percentage" -> new PercentagePromotion(name, value);
            case "flat" -> new FlatPromotion(name, value);
            default -> throw new IllegalArgumentException("Unsupported promotion type: " + type);
        };
    }
}
