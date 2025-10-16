package application.controller;

import application.model.Promotion;
import application.service.PromotionService;
import application.view.PromotionView;

public class PromotionController {
    private final PromotionService promotionService;
    private final PromotionView promotionView;

    public PromotionController(PromotionService promotionService, PromotionView promotionView) {
        this.promotionService = promotionService;
        this.promotionView = promotionView;
    }

    public Promotion createPromotion(String type) {
        Promotion promotion = promotionService.createPromotion(type);
        promotionView.showPromotion(promotion);
        return promotion;
    }
}
