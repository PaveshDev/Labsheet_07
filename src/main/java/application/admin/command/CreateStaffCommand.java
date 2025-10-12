package application.admin.command;

import application.admin.AdminDirectory;
import application.admin.Staff;

/**
 * Command to create a new staff member.
 */
public class CreateStaffCommand implements AdminCommand {
    private final AdminDirectory adminService;
    private final String staffName;
    private Staff createdStaff;

    public CreateStaffCommand(AdminDirectory adminService, String staffName) {
        this.adminService = adminService;
        this.staffName = staffName;
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
