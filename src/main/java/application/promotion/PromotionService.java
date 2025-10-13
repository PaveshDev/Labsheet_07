package application.promotion;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/**
 * Coordinates creation and retrieval of promotions.
 */
public class PromotionService {
    private final PromotionFactory factory;
    private final Map<String, Promotion> promotions = new LinkedHashMap<>();

    public PromotionService(PromotionFactory factory) {
        this.factory = Objects.requireNonNull(factory, "factory");
    }

    public Promotion createPromotion(String type, String name, double value) {
        String sanitizedType = sanitizeType(type);
        String sanitizedName = sanitizeName(name);
        validateValueForType(sanitizedType, value);

        String key = normalizeKey(sanitizedName);
        if (promotions.containsKey(key)) {
            throw new IllegalArgumentException("Promotion '" + sanitizedName + "' already exists");
        }

        Promotion promotion = factory.createPromotion(sanitizedType, sanitizedName, value);
        promotions.put(key, promotion);
        return promotion;
    }

    public Promotion findPromotion(String name) {
        if (name == null) {
            return null;
        }
        String trimmed = name.trim();
        if (trimmed.isEmpty()) {
            return null;
        }
        return promotions.get(normalizeKey(trimmed));
    }

    public Collection<Promotion> listPromotions() {
        return List.copyOf(promotions.values());
    }

    public Promotion deletePromotion(String name) {
        String sanitizedName = sanitizeName(name);
        Promotion removed = promotions.remove(normalizeKey(sanitizedName));
        if (removed == null) {
            throw new IllegalArgumentException("Promotion '" + sanitizedName + "' does not exist");
        }
        return removed;
    }

    public double applyPromotions(double amount, String... promotionNames) {
        double total = amount;
        if (promotionNames == null) {
            return total;
        }
        for (String name : promotionNames) {
            String sanitizedName = sanitizeName(name);
            Promotion promotion = promotions.get(normalizeKey(sanitizedName));
            if (promotion == null) {
                throw new IllegalArgumentException("Promotion '" + sanitizedName + "' does not exist");
            }
            total = promotion.apply(total);
        }
        return total;
    }

    private String sanitizeType(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Promotion type must not be null");
        }
        String trimmed = type.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Promotion type must not be blank");
        }
        return trimmed;
    }

    private String sanitizeName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Promotion name must not be null");
        }
        String trimmed = name.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Promotion name must not be blank");
        }
        return trimmed;
    }

    private String normalizeKey(String name) {
        return name.toLowerCase(Locale.ROOT);
    }

    private void validateValueForType(String type, double value) {
        String normalizedType = type.toLowerCase(Locale.ROOT);
        switch (normalizedType) {
            case "percentage" -> {
                if (value <= 0 || value > 1) {
                    throw new IllegalArgumentException("Percentage promotions must be greater than 0 and at most 1");
                }
            }
            case "flat" -> {
                if (value < 0) {
                    throw new IllegalArgumentException("Flat promotions must not be negative");
                }
            }
            default -> {
                // let the factory handle unsupported types
            }
        }
    }
}
