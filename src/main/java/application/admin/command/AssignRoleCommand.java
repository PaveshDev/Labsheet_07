package application.admin.command;

import application.admin.AdminService;
import application.admin.Role;
import application.admin.Staff;

import java.util.UUID;

/**
 * Command to assign a role to an existing staff member.
 */
public class AssignRoleCommand implements AdminCommand {
    private final AdminService adminService;
    private final UUID staffId;
    private final Role role;

    public AssignRoleCommand(AdminService adminService, UUID staffId, Role role) {
        this.adminService = adminService;
        this.staffId = staffId;
        this.role = role;
    }

    @Override
    public void execute() {
        Staff staff = adminService.assignRole(staffId, role);
        System.out.printf("Assigned role %s to %s%n", staff.getRole(), staff.getName());
    }
}
