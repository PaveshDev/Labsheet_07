package application.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Provides management operations for hotel staff from the administrator perspective.
 */
public class AdminService implements AdminDirectory {
    private final Map<UUID, Staff> staffDirectory = new HashMap<>();

    @Override
    public Staff createStaff(String name) {
        String trimmedName = name == null ? "" : name.trim();
        if (trimmedName.isEmpty()) {
            throw new IllegalArgumentException("Staff name must not be blank");
        }
        Staff staff = new Staff(UUID.randomUUID(), trimmedName);
        staffDirectory.put(staff.getId(), staff);
        return staff;
    }

    @Override
    public Staff assignRole(UUID staffId, Role role) {
        Staff staff = requireStaff(staffId);
        staff.setRole(role);
        return staff;
    }

    @Override
    public Staff viewStaff(UUID staffId) {
        return requireStaff(staffId);
    }

    @Override
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
