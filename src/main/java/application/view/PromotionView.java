package application.view;

import application.model.Promotion;

public class PromotionView {
    public void showPromotion(Promotion promotion) {
        System.out.println("[View] Promotion applied: " + promotion.getName());
    }
}
