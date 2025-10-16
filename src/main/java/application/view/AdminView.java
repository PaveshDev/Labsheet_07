package application.view;

import application.model.Staff;

import java.util.List;

public class AdminView {
    public void showStaffCreated(Staff staff) {
        System.out.println("[View] Staff member created: " + staff.getName());
    }

    public void showRoleAssigned(Staff staff) {
        System.out.println("[View] " + staff.getName() + " assigned role: " + staff.getRole());
    }

    public void showStaff(List<Staff> staffMembers) {
        System.out.println("[View] Staff directory:");
        staffMembers.forEach(staff ->
                System.out.println("  - " + staff.getName() + " (" + staff.getRole() + ")"));
    }
}
