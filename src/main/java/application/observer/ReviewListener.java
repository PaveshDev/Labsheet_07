package application.observer;

import application.model.Review;

public interface ReviewListener {
    void onNewReview(Review review);
}
