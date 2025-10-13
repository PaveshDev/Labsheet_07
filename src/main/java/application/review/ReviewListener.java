package application.review;

/**
 * Observer interface for receiving notifications when new reviews are created.
 */
public interface ReviewListener {
    void onReviewSubmitted(Review review);
}
