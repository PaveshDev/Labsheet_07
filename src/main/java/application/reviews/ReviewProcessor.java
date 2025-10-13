package application.reviews;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Template method defining the review publishing workflow.
 */
public abstract class ReviewProcessor {
    public final PublishedReview publishReview(String guestName, int rating, String comments) {
        validateInput(guestName, rating, comments);
        String sanitised = sanitise(comments);
        return storeReview(guestName.trim(), rating, sanitised);
    }

    private void validateInput(String guestName, int rating, String comments) {
        if (guestName == null || guestName.isBlank()) {
            throw new IllegalArgumentException("Guest name must not be blank");
        }
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        Objects.requireNonNull(comments, "Comments must not be null");
    }

    protected String sanitise(String comments) {
        return comments.trim();
    }

    protected abstract PublishedReview storeReview(String guestName, int rating, String comments);

    /**
     * Minimal representation of a published review.
     */
    public record PublishedReview(String guestName, int rating, String comments, LocalDateTime publishedAt) {
    }
}
