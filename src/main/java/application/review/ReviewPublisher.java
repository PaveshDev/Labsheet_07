package application.review;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Subject in the Observer pattern responsible for broadcasting review
 * submissions.
 */
public class ReviewPublisher {
    private final Set<ReviewListener> listeners = new LinkedHashSet<>();

    public void register(ReviewListener listener) {
        listeners.add(listener);
    }

    public void unregister(ReviewListener listener) {
        listeners.remove(listener);
    }

    public void publish(Review review) {
        for (ReviewListener listener : listeners) {
            listener.onReviewSubmitted(review);
        }
    }
}
