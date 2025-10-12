package application.admin;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Provides management operations for hotel staff from the administrator perspective.
 */
public class AdminService {
    private final Map<UUID, Staff> staffDirectory = new LinkedHashMap<>();

    public Staff createStaff(String name) {
        Staff staff = new Staff(UUID.randomUUID(), name);
        staffDirectory.put(staff.getId(), staff);
        return staff;
    }

    public void assignRole(UUID staffId, Role role) {
        Staff staff = requireStaff(staffId);
        staff.setRole(role);
    }

    public Staff viewStaff(UUID staffId) {
        return requireStaff(staffId);
    }

    public List<Staff> listStaff() {
        return new ArrayList<>(staffDirectory.values());
    }

    private Staff requireStaff(UUID staffId) {
        Staff staff = staffDirectory.get(staffId);
        if (staff == null) {
            throw new IllegalArgumentException("Staff member " + staffId + " is not registered");
        }
        return staff;
    }
}
