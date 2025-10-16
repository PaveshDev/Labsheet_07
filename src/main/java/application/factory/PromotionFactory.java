package application.factory;

import application.model.FlatPromotion;
import application.model.PercentagePromotion;
import application.model.Promotion;

/**
 * Factory responsible for creating promotion instances.
 */
public final class PromotionFactory {
    private PromotionFactory() {
    }

    public static Promotion createPromotion(String type) {
        if ("flat".equalsIgnoreCase(type)) {
            return new FlatPromotion("Flat $20", 20);
        }
        if ("percentage".equalsIgnoreCase(type)) {
            return new PercentagePromotion("Summer 15%", 0.15);
        }
        return new PercentagePromotion("Default 5%", 0.05);
    }
}
