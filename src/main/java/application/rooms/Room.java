package application.rooms;

import java.util.Objects;

/**
 * Represents a hotel room using the Builder design pattern to compose the
 * optional attributes. The builder keeps the Room constructor private and
 * ensures the required values (number and type) are always populated.
 */
public final class Room {
    private final int number;
    private final RoomType type;
    private final boolean hasSeaView;
    private final boolean hasBalcony;
    private final boolean smokingAllowed;

    private Room(Builder builder) {
        this.number = builder.number;
        this.type = builder.type;
        this.hasSeaView = builder.hasSeaView;
        this.hasBalcony = builder.hasBalcony;
        this.smokingAllowed = builder.smokingAllowed;
    }

    public int getNumber() {
        return number;
    }

    public RoomType getType() {
        return type;
    }

    public boolean hasSeaView() {
        return hasSeaView;
    }

    public boolean hasBalcony() {
        return hasBalcony;
    }

    public boolean isSmokingAllowed() {
        return smokingAllowed;
    }

    @Override
    public String toString() {
        return "Room " + number + " (" + type + ", seaView=" + hasSeaView
                + ", balcony=" + hasBalcony + ", smoking=" + smokingAllowed + ")";
    }

    /**
     * Builder for constructing {@link Room} instances with fluent configuration.
     */
    public static class Builder {
        private final int number;
        private final RoomType type;
        private boolean hasSeaView;
        private boolean hasBalcony;
        private boolean smokingAllowed;

        public Builder(int number, RoomType type) {
            if (number <= 0) {
                throw new IllegalArgumentException("Room number must be positive");
            }
            this.number = number;
            this.type = Objects.requireNonNull(type, "Room type must not be null");
        }

        public Builder seaView() {
            this.hasSeaView = true;
            return this;
        }

        public Builder balcony() {
            this.hasBalcony = true;
            return this;
        }

        public Builder smokingAllowed() {
            this.smokingAllowed = true;
            return this;
        }

        public Room build() {
            return new Room(this);
        }
    }
}
