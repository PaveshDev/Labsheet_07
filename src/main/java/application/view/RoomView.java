package application.view;

import application.model.Room;

import java.util.List;

public class RoomView {
    public void showRoomAdded(Room room) {
        System.out.println("[View] Added room: " + room);
    }

    public void showRooms(List<Room> rooms) {
        System.out.println("[View] Rooms:");
        rooms.forEach(room -> System.out.println("  - " + room));
    }
}
