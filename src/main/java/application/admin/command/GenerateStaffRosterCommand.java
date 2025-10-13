package application.admin.command;

import application.admin.AdminService;
import application.admin.Staff;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Command that prints a textual snapshot of the staff roster.
 */
public class GenerateStaffRosterCommand implements AdminCommand {
    private final AdminService adminService;

    public GenerateStaffRosterCommand(AdminService adminService) {
        if (adminService == null) {
            throw new IllegalArgumentException("adminService must not be null");
        }
        this.adminService = adminService;
    }

    @Override
    public void execute(Consumer<String> output) {
        Objects.requireNonNull(output, "output");
        var staffMembers = adminService.listStaff();
        if (staffMembers.isEmpty()) {
            output.accept("No staff registered");
            return;
        }
        for (Staff staff : staffMembers) {
            output.accept(String.format("%s - %s (%s)",
                    staff.getId(),
                    staff.getName(),
                    staff.getRole() == null ? "UNASSIGNED" : staff.getRole()));
        }
    }
}
