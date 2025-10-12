package application.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Provides management operations for hotel staff from the administrator perspective.
 */
public class AdminService implements AdminDirectory {
    private final Map<UUID, Staff> staffDirectory = new LinkedHashMap<>();

    @Override
    public Staff createStaff(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Staff name must not be blank");
        }

        Staff staff = new Staff(UUID.randomUUID(), name.trim());
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

    public Staff getStaff(UUID staffId) {
        return viewStaff(staffId);
    }

    @Override
    public List<Staff> listStaff() {
        return Collections.unmodifiableList(new ArrayList<>(staffDirectory.values()));
    }

    public boolean hasStaff(UUID staffId) {
        return staffDirectory.containsKey(staffId);
    }

    public List<Staff> getAllStaff() {
        return listStaff();
    }

    private Staff requireStaff(UUID staffId) {
        Staff staff = staffDirectory.get(staffId);
        if (staff == null) {
            throw new IllegalArgumentException("Staff member " + staffId + " is not registered");
        }
        return staff;
    }
}
