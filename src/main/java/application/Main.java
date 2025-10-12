package application;

import application.admin.AdminService;
import application.admin.Room;
import application.admin.command.AddRoomCommand;
import application.admin.command.AdminCommandInvoker;
import application.admin.command.GenerateInventoryReportCommand;
import application.admin.command.ToggleRoomAvailabilityCommand;
import application.admin.command.UpdateRoomRateCommand;

/**
 * Demonstrates the admin component of the hotel reservation system.
 * <p>
 * The component uses the Command design pattern to decouple the invocation of
 * admin actions from their implementation. Each admin operation (adding rooms,
 * updating rates, toggling availability, generating reports) is represented as a
 * concrete command object.
 */
public class Main {

    public static void main(String[] args) {
        AdminService adminService = new AdminService();
        AdminCommandInvoker invoker = new AdminCommandInvoker();

        invoker.register("add-room-101", new AddRoomCommand(adminService,
                new Room(101, "Deluxe", 150.0, true)));
        invoker.register("add-room-102", new AddRoomCommand(adminService,
                new Room(102, "Suite", 250.0, true)));
        invoker.register("update-rate-101", new UpdateRoomRateCommand(adminService, 101, 175.0));
        invoker.register("mark-102-maintenance", new ToggleRoomAvailabilityCommand(adminService, 102, false));
        invoker.register("print-report", new GenerateInventoryReportCommand(adminService));

        invoker.execute("add-room-101");
        invoker.execute("add-room-102");
        invoker.execute("update-rate-101");
        invoker.execute("mark-102-maintenance");
        invoker.execute("print-report");
    }
}
