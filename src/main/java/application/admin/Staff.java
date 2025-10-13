package application.admin;

import java.util.Objects;

/**
 * Represents a member of the hotel's staff managed by the admin component.
 */
public class Staff {
    private final String id;
    private final String name;
    private Role role;

    public Staff(String id, String name) {
        String normalizedId = Objects.requireNonNull(id, "id").trim();
        if (normalizedId.isEmpty()) {
            throw new IllegalArgumentException("id must not be blank");
        }
        this.id = normalizedId;
        String normalizedName = Objects.requireNonNull(name, "name").trim();
        if (normalizedName.isEmpty()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        this.name = normalizedName;
    }

    public String getId() {
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
