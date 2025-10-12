package application;

import application.admin.AdminService;
import application.admin.Role;
import application.admin.command.AdminCommandInvoker;
import application.admin.command.AssignRoleCommand;
import application.admin.command.CreateStaffCommand;
import application.admin.command.GenerateStaffRosterCommand;
import application.admin.command.AdminCommand;

/**
 * Demonstrates the admin component of the hotel reservation system.
 * <p>
 * The component uses the Command design pattern to decouple the invocation of
 * admin actions from their implementation. Each admin operation (hiring staff,
 * assigning roles, generating roster reports) is represented as a concrete
 * command object.
 */
public class Main {

    public static void main(String[] args) {
        AdminService adminService = new AdminService();
        AdminCommandInvoker invoker = new AdminCommandInvoker();

        CreateStaffCommand hireReceptionist = new CreateStaffCommand(adminService, "Anika Perera");
        CreateStaffCommand hireAccountant = new CreateStaffCommand(adminService, "Ruwan Silva");
        CreateStaffCommand hireHousekeeper = new CreateStaffCommand(adminService, "Dulani Fernando");

        registerAndExecute(invoker, "hire-receptionist", hireReceptionist);
        registerAndExecute(invoker, "hire-accountant", hireAccountant);
        registerAndExecute(invoker, "hire-housekeeper", hireHousekeeper);

        invoker.register("assign-receptionist-role", new AssignRoleCommand(adminService,
                hireReceptionist.getCreatedStaff().getId(), Role.RECEPTIONIST));
        invoker.register("assign-accountant-role", new AssignRoleCommand(adminService,
                hireAccountant.getCreatedStaff().getId(), Role.ACCOUNTANT));
        invoker.register("assign-housekeeping-role", new AssignRoleCommand(adminService,
                hireHousekeeper.getCreatedStaff().getId(), Role.HOUSEKEEPING));

        invoker.execute("assign-receptionist-role");
        invoker.execute("assign-accountant-role");
        invoker.execute("assign-housekeeping-role");

        invoker.register("print-roster", new GenerateStaffRosterCommand(adminService));
        invoker.execute("print-roster");
    }

    private static void registerAndExecute(AdminCommandInvoker invoker, String name, AdminCommand command) {
        invoker.register(name, command);
        invoker.execute(name);
    }
}
