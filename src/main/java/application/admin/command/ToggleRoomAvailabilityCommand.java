package application.admin.command;

import application.admin.AdminService;

/**
 * Command to make a room available or unavailable.
 */
public class ToggleRoomAvailabilityCommand implements AdminCommand {
    private final AdminService adminService;
    private final int roomNumber;
    private final boolean available;

    public ToggleRoomAvailabilityCommand(AdminService adminService, int roomNumber, boolean available) {
        this.adminService = adminService;
        this.roomNumber = roomNumber;
        this.available = available;
    }

    @Override
    public void execute() {
        adminService.setAvailability(roomNumber, available);
        System.out.printf("Set room %d availability to %b%n", roomNumber, available);
    }
}
