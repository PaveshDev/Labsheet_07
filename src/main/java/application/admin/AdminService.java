package application.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides management operations for hotel rooms from the administrator perspective.
 */
public class AdminService {
    private final Map<Integer, Room> rooms = new HashMap<>();

    public void addRoom(Room room) {
        rooms.put(room.getRoomNumber(), room);
    }

    public void updateRate(int roomNumber, double newRate) {
        Room room = requireRoom(roomNumber);
        room.setRatePerNight(newRate);
    }

    public void setAvailability(int roomNumber, boolean available) {
        Room room = requireRoom(roomNumber);
        room.setAvailable(available);
    }

    public Room viewRoom(int roomNumber) {
        return requireRoom(roomNumber);
    }

    public List<Room> listRooms() {
        return new ArrayList<>(rooms.values());
    }

    private Room requireRoom(int roomNumber) {
        Room room = rooms.get(roomNumber);
        if (room == null) {
            throw new IllegalArgumentException("Room " + roomNumber + " is not registered");
        }
        return room;
    }
}
