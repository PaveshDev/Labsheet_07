package application.view;

import application.model.Review;

public class ReviewView {
    public void showReviewSubmitted(Review review) {
        System.out.println("[View] Review submitted by " + review.getCustomerName());
    }
}
