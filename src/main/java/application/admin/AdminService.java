package application.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Provides management operations for hotel rooms from the administrator perspective.
 */
public class AdminService implements AdminDirectory {
    private final Map<Integer, Room> rooms = new HashMap<>();
    private final Map<UUID, Staff> staffDirectory = new HashMap<>();

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

    @Override
    public Staff createStaff(String name) {
        Staff staff = new Staff(UUID.randomUUID(), name);
        staffDirectory.put(staff.getId(), staff);
        return staff;
    }

    @Override
    public Staff assignRole(UUID staffId, Role role) {
        Staff staff = requireStaff(staffId);
        staff.setRole(role);
        return staff;
    }

    @Override
    public Staff viewStaff(UUID staffId) {
        return requireStaff(staffId);
    }

    @Override
    public List<Staff> listStaff() {
        return new ArrayList<>(staffDirectory.values());
    }

    private Room requireRoom(int roomNumber) {
        Room room = rooms.get(roomNumber);
        if (room == null) {
            throw new IllegalArgumentException("Room " + roomNumber + " is not registered");
        }
        return room;
    }

    private Staff requireStaff(UUID staffId) {
        Staff staff = staffDirectory.get(staffId);
        if (staff == null) {
            throw new IllegalArgumentException("Staff member " + staffId + " is not registered");
        }
        return staff;
    }
}
