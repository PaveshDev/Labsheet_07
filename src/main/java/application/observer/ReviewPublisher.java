package application.observer;

import application.model.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewPublisher {
    private final List<ReviewListener> listeners = new ArrayList<>();

    public void register(ReviewListener listener) {
        listeners.add(listener);
    }

    public void unregister(ReviewListener listener) {
        listeners.remove(listener);
    }

    public void publish(Review review) {
        listeners.forEach(listener -> listener.onNewReview(review));
    }
}
