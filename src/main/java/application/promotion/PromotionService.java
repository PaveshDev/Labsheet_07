package application.promotion;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Coordinates creation and retrieval of promotions.
 */
public class PromotionService {
    private final PromotionFactory factory;
    private final Map<String, Promotion> promotions = new LinkedHashMap<>();

    public PromotionService(PromotionFactory factory) {
        this.factory = factory;
    }

    public Promotion createPromotion(String type, String name, double value) {
        Promotion promotion = factory.createPromotion(type, name, value);
        promotions.put(promotion.name(), promotion);
        return promotion;
    }

    public Promotion findPromotion(String name) {
        return promotions.get(name);
    }

    public Collection<Promotion> listPromotions() {
        return Collections.unmodifiableCollection(promotions.values());
    }

    public void deactivatePromotion(String name) {
        promotions.remove(name);
    }

    public double applyPromotions(double amount) {
        double total = amount;
        for (Promotion promotion : promotions.values()) {
            total = promotion.apply(total);
        }
        return total;
    }
}
