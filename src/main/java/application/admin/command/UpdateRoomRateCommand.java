package application.admin.command;

import application.admin.AdminService;

/**
 * Command to change the nightly rate of a room.
 */
public class UpdateRoomRateCommand implements AdminCommand {
    private final AdminService adminService;
    private final int roomNumber;
    private final double newRate;

    public UpdateRoomRateCommand(AdminService adminService, int roomNumber, double newRate) {
        this.adminService = adminService;
        this.roomNumber = roomNumber;
        this.newRate = newRate;
    }

    @Override
    public void execute() {
        adminService.updateRate(roomNumber, newRate);
        System.out.printf("Updated room %d rate to %.2f%n", roomNumber, newRate);
    }
}
