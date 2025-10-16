package application.repository;

import application.model.Room;
import application.singleton.DatabaseConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryRoomRepository implements RoomRepository {
    private static final String TABLE_NAME = "rooms";
    private final DatabaseConnection connection;

    public InMemoryRoomRepository(DatabaseConnection connection) {
        this.connection = connection;
    }

    private List<Room> table() {
        return connection.getTable(TABLE_NAME);
    }

    @Override
    public void save(Room room) {
        List<Room> rooms = table();
        rooms.removeIf(existing -> existing.getNumber().equals(room.getNumber()));
        rooms.add(room);
    }

    @Override
    public Optional<Room> findByNumber(String number) {
        return table().stream().filter(r -> r.getNumber().equals(number)).findFirst();
    }

    @Override
    public List<Room> findAll() {
        return new ArrayList<>(table());
    }
}
