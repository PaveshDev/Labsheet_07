package application.admin.command;

import application.admin.AdminService;
import application.admin.Staff;

import java.util.Objects;
import java.util.function.Consumer;

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
    public void execute(Consumer<String> output) {
        Objects.requireNonNull(output, "output");
        createdStaff = adminService.createStaff(staffName);
        output.accept(String.format(
                "Created staff member %s with id %s",
                createdStaff.getName(),
                createdStaff.getId()));
    }

    public Staff getCreatedStaff() {
        if (createdStaff == null) {
            throw new IllegalStateException("Staff member has not been created yet. Execute the command first.");
        }
        return createdStaff;
    }
}
