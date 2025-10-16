package application.controller;

import application.model.Room;
import application.service.RoomService;
import application.view.RoomView;

public class RoomController {
    private final RoomService roomService;
    private final RoomView roomView;

    public RoomController(RoomService roomService, RoomView roomView) {
        this.roomService = roomService;
        this.roomView = roomView;
    }

    public void addRoom(Room room) {
        roomService.addRoom(room);
        roomView.showRoomAdded(room);
    }

    public void displayRooms() {
        roomView.showRooms(roomService.listRooms());
    }
}
