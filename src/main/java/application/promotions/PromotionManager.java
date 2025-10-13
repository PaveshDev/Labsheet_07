package application.promotions;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Acts as the subject in the Observer pattern.
 */
public class PromotionManager {
    private final Set<PromotionSubscriber> subscribers = new LinkedHashSet<>();

    public void subscribe(PromotionSubscriber subscriber) {
        if (subscriber == null) {
            throw new IllegalArgumentException("Subscriber must not be null");
        }
        subscribers.add(subscriber);
    }

    public void unsubscribe(PromotionSubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void announcePromotion(PromotionEvent event) {
        if (event == null) {
            throw new IllegalArgumentException("Promotion event must not be null");
        }
        subscribers.forEach(subscriber -> subscriber.onPromotion(event));
    }
}
