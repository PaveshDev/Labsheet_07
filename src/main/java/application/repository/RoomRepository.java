package application.repository;

import application.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    void save(Room room);

    Optional<Room> findByNumber(String number);

    List<Room> findAll();
}
