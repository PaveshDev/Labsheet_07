package application.admin.command;

import application.admin.AdminService;
import application.admin.Room;

/**
 * Command to add a new room definition.
 */
public class AddRoomCommand implements AdminCommand {
    private final AdminService adminService;
    private final Room room;

    public AddRoomCommand(AdminService adminService, Room room) {
        this.adminService = adminService;
        this.room = room;
    }

    @Override
    public void execute() {
        adminService.addRoom(room);
        System.out.println("Added room: " + room);
    }
}
