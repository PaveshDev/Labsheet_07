package application.admin;

import java.util.Objects;

/**
 * Represents a hotel room managed by the admin component.
 */
public class Room {
    private final int roomNumber;
    private final String type;
    private double ratePerNight;
    private boolean available;

    public Room(int roomNumber, String type, double ratePerNight, boolean available) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.ratePerNight = ratePerNight;
        this.available = available;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public double getRatePerNight() {
        return ratePerNight;
    }

    public void setRatePerNight(double ratePerNight) {
        this.ratePerNight = ratePerNight;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", type='" + type + '\'' +
                ", ratePerNight=" + ratePerNight +
                ", available=" + available +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return roomNumber == room.roomNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }
}
