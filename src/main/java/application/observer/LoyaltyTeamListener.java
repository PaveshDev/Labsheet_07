package application.observer;

import application.model.Review;

public class LoyaltyTeamListener implements ReviewListener {
    @Override
    public void onNewReview(Review review) {
        System.out.println("[Observer] Loyalty team sees feedback: " + review.getComment());
    }
}
