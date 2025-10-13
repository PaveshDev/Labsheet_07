package application.admin;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides management operations for hotel staff from the administrator perspective.
 */
public class AdminService implements AdminDirectory {
    private final Map<String, Staff> staffDirectory = new LinkedHashMap<>();
    private int nextStaffNumber = 1;

    @Override
    public Staff createStaff(String name) {
        String trimmedName = name == null ? "" : name.trim();
        if (trimmedName.isEmpty()) {
            throw new IllegalArgumentException("Staff name must not be blank");
        }
        ensureNameIsUnique(trimmedName);

        Staff staff = new Staff(generateNextId(), trimmedName);
        staffDirectory.put(staff.getId(), staff);
        return staff;
    }

    @Override
    public Staff assignRole(String staffId, Role role) {
        Staff staff = requireStaff(staffId);
        if (role == null) {
            throw new IllegalArgumentException("Role must not be null");
        }
        staff.setRole(role);
        return staff;
    }

    @Override
    public Staff viewStaff(String staffId) {
        return requireStaff(staffId);
    }

    @Override
    public List<Staff> listStaff() {
        return List.copyOf(staffDirectory.values());
    }

    private Staff requireStaff(String staffId) {
        if (staffId == null) {
            throw new IllegalArgumentException("Staff id must not be null");
        }
        Staff staff = staffDirectory.get(staffId);
        if (staff == null) {
            throw new IllegalArgumentException("Staff member " + staffId + " is not registered");
        }
        return staff;
    }

    private String generateNextId() {
        String id = String.format("%03d", nextStaffNumber);
        nextStaffNumber++;
        return id;
    }

    private void ensureNameIsUnique(String name) {
        boolean nameAlreadyExists = staffDirectory.values().stream()
                .anyMatch(staff -> staff.getName().equalsIgnoreCase(name));
        if (nameAlreadyExists) {
            throw new IllegalArgumentException("Staff member with name '" + name + "' already exists");
        }
    }
}
