package application;

import application.admin.AdminService;
import application.admin.Role;
import application.admin.command.AdminCommandInvoker;
import application.admin.command.AssignRoleCommand;
import application.admin.command.CreateStaffCommand;
import application.admin.command.GenerateStaffRosterCommand;

/**
 * Demonstrates the admin component of the hotel reservation system.
 * <p>
 * The component uses the Command design pattern to manage staff creation and
 * role assignment actions through dedicated command objects.
 */
public class Main {

    public static void main(String[] args) {
        AdminService adminService = new AdminService();
        AdminCommandInvoker invoker = new AdminCommandInvoker();

        CreateStaffCommand createReceptionist = new CreateStaffCommand(adminService, "Alice Johnson");
        CreateStaffCommand createHousekeeper = new CreateStaffCommand(adminService, "Ben King");

        invoker.register("create-alice", createReceptionist);
        invoker.register("create-ben", createHousekeeper);
        invoker.register("print-roster", new GenerateStaffRosterCommand(adminService));

        invoker.execute("create-alice");
        invoker.execute("create-ben");

        invoker.register("assign-alice", new AssignRoleCommand(
                adminService,
                createReceptionist.getCreatedStaff().getId(),
                Role.MANAGER));
        invoker.register("assign-ben", new AssignRoleCommand(
                adminService,
                createHousekeeper.getCreatedStaff().getId(),
                Role.HOUSEKEEPING));

        invoker.execute("assign-alice");
        invoker.execute("assign-ben");
        invoker.execute("print-roster");
    }
}
