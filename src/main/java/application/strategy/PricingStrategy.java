package application.strategy;

import application.model.Room;

public interface PricingStrategy {
    double calculatePrice(Room room, int nights);
}
