package application.admin;

import java.util.List;

/**
 * API for admin operations that manage hotel staff members.
 */
public interface AdminDirectory {
    Staff createStaff(String name);

    Staff assignRole(String staffId, Role role);

    Staff viewStaff(String staffId);

    List<Staff> listStaff();
}
