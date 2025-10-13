package application.admin;

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
        String trimmedName = name == null ? "" : name.trim();
        if (trimmedName.isEmpty()) {
            throw new IllegalArgumentException("Staff name must not be blank");
        }
        ensureNameIsUnique(trimmedName);

        Staff staff = new Staff(UUID.randomUUID(), trimmedName);
        staffDirectory.put(staff.getId(), staff);
        return staff;
    }

    @Override
    public Staff assignRole(UUID staffId, Role role) {
        Staff staff = requireStaff(staffId);
        if (role == null) {
            throw new IllegalArgumentException("Role must not be null");
        }
        staff.setRole(role);
        return staff;
    }

    @Override
    public Staff viewStaff(UUID staffId) {
        return requireStaff(staffId);
    }

    @Override
    public List<Staff> listStaff() {
        return List.copyOf(staffDirectory.values());
    }

    private Staff requireStaff(UUID staffId) {
        if (staffId == null) {
            throw new IllegalArgumentException("Staff id must not be null");
        }
        Staff staff = staffDirectory.get(staffId);
        if (staff == null) {
            throw new IllegalArgumentException("Staff member " + staffId + " is not registered");
        }
        return staff;
    }

    private void ensureNameIsUnique(String name) {
        boolean nameAlreadyExists = staffDirectory.values().stream()
                .anyMatch(staff -> staff.getName().equalsIgnoreCase(name));
        if (nameAlreadyExists) {
            throw new IllegalArgumentException("Staff member with name '" + name + "' already exists");
        }
    }
}
