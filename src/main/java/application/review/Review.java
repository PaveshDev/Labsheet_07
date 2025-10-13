package application.review;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents feedback from a guest.
 */
public class Review {
    private final String customerId;
    private final int rating;
    private final String comments;
    private final LocalDate submittedOn = LocalDate.now();

    public Review(String customerId, int rating, String comments) {
        this.customerId = Objects.requireNonNull(customerId, "customerId");
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("rating must be between 1 and 5");
        }
        this.rating = rating;
        this.comments = comments == null ? "" : comments;
    }

    public String getCustomerId() {
        return customerId;
    }

    public int getRating() {
        return rating;
    }

    public String getComments() {
        return comments;
    }

    public LocalDate getSubmittedOn() {
        return submittedOn;
    }

    @Override
    public String toString() {
        return "Review{" +
                "customerId='" + customerId + '\'' +
                ", rating=" + rating +
                ", comments='" + comments + '\'' +
                ", submittedOn=" + submittedOn +
                '}';
    }
}
