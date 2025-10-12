package application.admin;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents a member of the hotel's staff managed by the admin component.
 */
public class Staff {
    private final UUID id;
    private final String name;
    private Role role;

    public Staff(UUID id, String name) {
        this.id = Objects.requireNonNull(id, "id");
        this.name = Objects.requireNonNull(name, "name");
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = Objects.requireNonNull(role, "role");
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role=" + (role == null ? "UNASSIGNED" : role) +
                '}';
    }
}
