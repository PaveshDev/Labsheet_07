package application.room;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a hotel room built via the Builder pattern to make optional
 * configuration flags explicit and maintainable.
 */
public final class Room {
    private final String roomNumber;
    private final String type;
    private final int capacity;
    private final double nightlyRate;
    private final Set<String> amenities;

    private Room(Builder builder) {
        this.roomNumber = builder.roomNumber;
        this.type = builder.type;
        this.capacity = builder.capacity;
        this.nightlyRate = builder.nightlyRate;
        this.amenities = Collections.unmodifiableSet(new LinkedHashSet<>(builder.amenities));
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getNightlyRate() {
        return nightlyRate;
    }

    public Set<String> getAmenities() {
        return amenities;
    }

    @Override
    public String toString() {
        return String.format("Room %s (%s) - sleeps %d - $%.2f/night - amenities: %s",
                roomNumber,
                type,
                capacity,
                nightlyRate,
                amenities);
    }

    /**
     * Builder for constructing {@link Room} instances with optional amenities.
     */
    public static class Builder {
        private final String roomNumber;
        private String type = "Standard";
        private int capacity = 1;
        private double nightlyRate = 0;
        private final Set<String> amenities = new LinkedHashSet<>();

        public Builder(String roomNumber) {
            this.roomNumber = Objects.requireNonNull(roomNumber, "roomNumber");
        }

        public Builder type(String type) {
            this.type = Objects.requireNonNull(type, "type");
            return this;
        }

        public Builder capacity(int capacity) {
            if (capacity <= 0) {
                throw new IllegalArgumentException("capacity must be positive");
            }
            this.capacity = capacity;
            return this;
        }

        public Builder nightlyRate(double nightlyRate) {
            if (nightlyRate < 0) {
                throw new IllegalArgumentException("nightlyRate must not be negative");
            }
            this.nightlyRate = nightlyRate;
            return this;
        }

        public Builder addAmenity(String amenity) {
            if (amenity != null && !amenity.isBlank()) {
                amenities.add(amenity.trim());
            }
            return this;
        }

        public Room build() {
            if (nightlyRate == 0) {
                throw new IllegalStateException("nightlyRate must be specified");
            }
            return new Room(this);
        }
    }
}
