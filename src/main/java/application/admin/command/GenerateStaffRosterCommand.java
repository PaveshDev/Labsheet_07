package application.admin.command;

import application.admin.AdminService;
import application.admin.Staff;

/**
 * Command that prints a textual snapshot of the staff roster.
 */
public class GenerateStaffRosterCommand implements AdminCommand {
    private final AdminService adminService;

    public GenerateStaffRosterCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void execute() {
        System.out.println("=== Staff Roster ===");
        if (adminService.listStaff().isEmpty()) {
            System.out.println("No staff registered");
            return;
        }
        for (Staff staff : adminService.listStaff()) {
            System.out.printf("%s - %s (%s)%n",
                    staff.getId(),
                    staff.getName(),
                    staff.getRole() == null ? "UNASSIGNED" : staff.getRole());
        }
    }
}
