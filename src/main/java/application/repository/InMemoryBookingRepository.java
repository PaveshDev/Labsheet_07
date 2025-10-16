package application.repository;

import application.model.Booking;
import application.singleton.DatabaseConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryBookingRepository implements BookingRepository {
    private static final String TABLE_NAME = "bookings";
    private final DatabaseConnection connection;

    public InMemoryBookingRepository(DatabaseConnection connection) {
        this.connection = connection;
    }

    private List<Booking> table() {
        return connection.getTable(TABLE_NAME);
    }

    @Override
    public void save(Booking booking) {
        List<Booking> bookings = table();
        bookings.removeIf(existing -> existing.getId().equals(booking.getId()));
        bookings.add(booking);
    }

    @Override
    public Optional<Booking> findById(String id) {
        return table().stream().filter(b -> b.getId().equals(id)).findFirst();
    }

    @Override
    public List<Booking> findAll() {
        return new ArrayList<>(table());
    }
}
