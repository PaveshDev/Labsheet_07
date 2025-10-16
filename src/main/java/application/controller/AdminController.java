package application.controller;

import application.model.Role;
import application.model.Staff;
import application.service.AdminService;
import application.view.AdminView;

public class AdminController {
    private final AdminService adminService;
    private final AdminView adminView;

    public AdminController(AdminService adminService, AdminView adminView) {
        this.adminService = adminService;
        this.adminView = adminView;
    }

    public void createStaff(String id, String name) {
        Staff staff = adminService.registerStaff(id, name);
        adminView.showStaffCreated(staff);
    }

    public void assignRole(String staffId, Role role) {
        Staff staff = adminService.assignRole(staffId, role);
        adminView.showRoleAssigned(staff);
    }

    public void displayStaff() {
        adminView.showStaff(adminService.listStaff());
    }
}
