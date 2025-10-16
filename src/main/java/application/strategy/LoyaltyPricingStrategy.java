package application.strategy;

import application.model.Room;

/**
 * Applies a simple loyalty discount.
 */
public class LoyaltyPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(Room room, int nights) {
        return room.getBasePrice() * nights * 0.9; // 10% discount
    }
}
