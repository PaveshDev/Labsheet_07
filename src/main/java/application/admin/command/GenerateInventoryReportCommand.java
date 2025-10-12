package application.admin.command;

import application.admin.AdminService;
import application.admin.Room;

/**
 * Command that prints a textual snapshot of the room inventory.
 */
public class GenerateInventoryReportCommand implements AdminCommand {
    private final AdminService adminService;

    public GenerateInventoryReportCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void execute() {
        System.out.println("=== Inventory Report ===");
        if (adminService.listRooms().isEmpty()) {
            System.out.println("No rooms registered");
            return;
        }
        for (Room room : adminService.listRooms()) {
            System.out.printf("Room %d [%s] - Rate: %.2f - Available: %b%n",
                    room.getRoomNumber(), room.getType(), room.getRatePerNight(), room.isAvailable());
        }
    }
}
