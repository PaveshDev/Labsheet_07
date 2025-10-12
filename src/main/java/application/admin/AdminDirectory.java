package application.admin;

import java.util.List;
import java.util.UUID;

/**
 * API for admin operations that manage hotel staff members.
 */
public interface AdminDirectory {
    Staff createStaff(String name);

    Staff assignRole(UUID staffId, Role role);

    Staff viewStaff(UUID staffId);

    List<Staff> listStaff();
}
