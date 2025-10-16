package application.service;

import application.model.Room;
import application.repository.RoomRepository;

import java.util.List;

public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public void addRoom(Room room) {
        roomRepository.save(room);
    }

    public List<Room> listRooms() {
        return roomRepository.findAll();
    }
}
