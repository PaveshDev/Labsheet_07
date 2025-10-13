package application.review;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Tracks highly rated reviews to reward loyal guests.
 */
public class LoyaltyTeamListener implements ReviewListener {
    private final List<Review> positiveReviews = new ArrayList<>();

    @Override
    public void onReviewSubmitted(Review review) {
        if (review.getRating() >= 4) {
            positiveReviews.add(review);
        }
    }

    public List<Review> getPositiveReviews() {
        return Collections.unmodifiableList(positiveReviews);
    }
}
