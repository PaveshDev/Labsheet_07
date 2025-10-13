package application.admin.command;

import application.admin.AdminService;
import application.admin.Role;
import application.admin.Staff;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Command to assign a role to an existing staff member.
 */
public class AssignRoleCommand implements AdminCommand {
    private final AdminService adminService;
    private final String staffId;
    private final Role role;

    public AssignRoleCommand(AdminService adminService, String staffId, Role role) {
        if (adminService == null) {
            throw new IllegalArgumentException("adminService must not be null");
        }
        if (staffId == null) {
            throw new IllegalArgumentException("staffId must not be null");
        }
        if (role == null) {
            throw new IllegalArgumentException("role must not be null");
        }
        this.adminService = adminService;
        this.staffId = staffId;
        this.role = role;
    }

    @Override
    public void execute(Consumer<String> output) {
        Objects.requireNonNull(output, "output");
        adminService.assignRole(staffId, role);
        Staff staff = adminService.viewStaff(staffId);
        output.accept(String.format("Assigned role %s to %s", staff.getRole(), staff.getName()));
    }
}
