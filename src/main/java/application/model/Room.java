package application.model;

import java.util.Objects;

/**
 * Room entity kept deliberately small for clarity.
 */
public class Room {
    private final String number;
    private final String type;
    private final double basePrice;

    public Room(String number, String type, double basePrice) {
        this.number = Objects.requireNonNull(number, "number required");
        this.type = Objects.requireNonNull(type, "type required");
        this.basePrice = basePrice;
    }

    public String getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public double getBasePrice() {
        return basePrice;
    }

    @Override
    public String toString() {
        return type + " room " + number + " ($" + basePrice + ")";
    }
}
