package application.review;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Concrete observer capturing submitted reviews for later reporting.
 */
public class AdminReviewListener implements ReviewListener {
    private final List<Review> capturedReviews = new ArrayList<>();

    @Override
    public void onReviewSubmitted(Review review) {
        capturedReviews.add(review);
    }

    public List<Review> getCapturedReviews() {
        return Collections.unmodifiableList(capturedReviews);
    }
}
