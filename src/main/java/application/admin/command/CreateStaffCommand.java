package application.admin.command;

import application.admin.AdminService;
import application.admin.Staff;

/**
 * Command to create a new staff member.
 */
public class CreateStaffCommand implements AdminCommand {
    private final AdminService adminService;
    private final String staffName;
    private Staff createdStaff;

    public CreateStaffCommand(AdminService adminService, String staffName) {
        if (adminService == null) {
            throw new IllegalArgumentException("adminService must not be null");
        }
        String normalizedName = staffName == null ? "" : staffName.trim();
        if (normalizedName.isEmpty()) {
            throw new IllegalArgumentException("staffName must not be blank");
        }
        this.adminService = adminService;
        this.staffName = normalizedName;
    }

    @Override
    public void execute() {
        createdStaff = adminService.createStaff(staffName);
        System.out.printf("Created staff member %s with id %s%n", createdStaff.getName(), createdStaff.getId());
    }

    public Staff getCreatedStaff() {
        if (createdStaff == null) {
            throw new IllegalStateException("Staff member has not been created yet. Execute the command first.");
        }
        return createdStaff;
    }
}
