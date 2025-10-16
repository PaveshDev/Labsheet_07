package application.service;

import application.factory.PromotionFactory;
import application.model.Promotion;

public class PromotionService {
    public Promotion createPromotion(String type) {
        return PromotionFactory.createPromotion(type);
    }
}
