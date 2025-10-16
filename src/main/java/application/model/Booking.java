package application.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Booking entity built using the Builder pattern to highlight how
 * complex aggregates can be created in a readable way.
 */
public class Booking {
    private final String id;
    private final Customer customer;
    private final Room room;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;
    private final double totalPrice;

    private Booking(Builder builder) {
        this.id = builder.id;
        this.customer = builder.customer;
        this.room = builder.room;
        this.checkInDate = builder.checkInDate;
        this.checkOutDate = builder.checkOutDate;
        this.totalPrice = builder.totalPrice;
    }

    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", customer=" + customer.getName() +
                ", room=" + room.getNumber() +
                ", price=" + totalPrice +
                '}';
    }

    /**
     * Builder used for constructing bookings in tests and controllers.
     */
    public static class Builder {
        private final String id;
        private Customer customer;
        private Room room;
        private LocalDate checkInDate;
        private LocalDate checkOutDate;
        private double totalPrice;

        public Builder(String id) {
            this.id = Objects.requireNonNull(id, "id required");
        }

        public Builder customer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Builder room(Room room) {
            this.room = room;
            return this;
        }

        public Builder checkInDate(LocalDate checkInDate) {
            this.checkInDate = checkInDate;
            return this;
        }

        public Builder checkOutDate(LocalDate checkOutDate) {
            this.checkOutDate = checkOutDate;
            return this;
        }

        public Builder totalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Booking build() {
            Objects.requireNonNull(customer, "customer required");
            Objects.requireNonNull(room, "room required");
            Objects.requireNonNull(checkInDate, "check-in required");
            Objects.requireNonNull(checkOutDate, "check-out required");
            return new Booking(this);
        }
    }
}
