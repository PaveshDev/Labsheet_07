package application.room;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Stores rooms created through the builder and provides simple lookup operations.
 */
public class RoomCatalog {
    private final Map<String, Room> rooms = new LinkedHashMap<>();

    public void register(Room room) {
        rooms.put(room.getRoomNumber(), room);
    }

    public Room findByNumber(String roomNumber) {
        return rooms.get(roomNumber);
    }

    public Collection<Room> listRooms() {
        return rooms.values();
    }
}
