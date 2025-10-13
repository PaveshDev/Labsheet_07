package application.reviews;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple review processor that stores reviews in memory.
 */
public class InMemoryReviewProcessor extends ReviewProcessor {
    private final List<PublishedReview> reviews = new ArrayList<>();

    @Override
    protected PublishedReview storeReview(String guestName, int rating, String comments) {
        PublishedReview review = new PublishedReview(guestName, rating, comments, LocalDateTime.now());
        reviews.add(review);
        return review;
    }

    public List<PublishedReview> listReviews() {
        return List.copyOf(reviews);
    }
}
