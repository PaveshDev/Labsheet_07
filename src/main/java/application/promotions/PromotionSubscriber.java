package application.promotions;

/**
 * Observer that reacts to promotion updates.
 */
public interface PromotionSubscriber {
    void onPromotion(PromotionEvent event);
}
