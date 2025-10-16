package application.service;

import application.model.Review;
import application.observer.ReviewPublisher;

public class ReviewService {
    private final ReviewPublisher publisher;

    public ReviewService(ReviewPublisher publisher) {
        this.publisher = publisher;
    }

    public void submitReview(Review review) {
        publisher.publish(review);
    }
}
