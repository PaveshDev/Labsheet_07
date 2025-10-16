package application.strategy;

import application.model.Room;

/**
 * Default pricing based on room base price.
 */
public class StandardPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(Room room, int nights) {
        return room.getBasePrice() * nights;
    }
}
