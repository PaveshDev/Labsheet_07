package application.service;

import application.model.Role;
import application.model.Staff;
import application.repository.StaffRepository;

import java.util.List;

public class AdminService {
    private final StaffRepository staffRepository;

    public AdminService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public Staff registerStaff(String id, String name) {
        Staff staff = new Staff(id, name);
        staffRepository.save(staff);
        return staff;
    }

    public Staff assignRole(String staffId, Role role) {
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new IllegalArgumentException("Staff not found: " + staffId));
        staff.setRole(role);
        staffRepository.save(staff);
        return staff;
    }

    public List<Staff> listStaff() {
        return staffRepository.findAll();
    }
}
