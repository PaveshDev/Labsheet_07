package application.controller;

import application.model.Review;
import application.service.ReviewService;
import application.view.ReviewView;

public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewView reviewView;

    public ReviewController(ReviewService reviewService, ReviewView reviewView) {
        this.reviewService = reviewService;
        this.reviewView = reviewView;
    }

    public void submitReview(Review review) {
        reviewService.submitReview(review);
        reviewView.showReviewSubmitted(review);
    }
}
