package application.observer;

import application.model.Review;

public class AdminReviewListener implements ReviewListener {
    @Override
    public void onNewReview(Review review) {
        System.out.println("[Observer] Admin notified of review from " + review.getCustomerName());
    }
}
