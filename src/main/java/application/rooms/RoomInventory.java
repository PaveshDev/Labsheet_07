package application.rooms;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Stores rooms and exposes read-only access to the catalog.
 */
public class RoomInventory {
    private final Map<Integer, Room> rooms = new LinkedHashMap<>();

    public Room addRoom(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Room must not be null");
        }
        if (rooms.containsKey(room.getNumber())) {
            throw new IllegalArgumentException("Room " + room.getNumber() + " already exists");
        }
        rooms.put(room.getNumber(), room);
        return room;
    }

    public Room findRoom(int number) {
        Room room = rooms.get(number);
        if (room == null) {
            throw new IllegalArgumentException("Room " + number + " does not exist");
        }
        return room;
    }

    public List<Room> listRooms() {
        return List.copyOf(rooms.values());
    }
}
