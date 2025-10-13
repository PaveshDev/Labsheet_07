package application.promotions;

/**
 * Carries details of a promotion sent to observers.
 */
public record PromotionEvent(String code, String description, double discountPercentage) {
}
