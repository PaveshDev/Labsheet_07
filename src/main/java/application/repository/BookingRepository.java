package application.repository;

import application.model.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingRepository {
    void save(Booking booking);

    Optional<Booking> findById(String id);

    List<Booking> findAll();
}
