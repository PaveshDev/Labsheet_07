package application.model;

/**
 * Simple model object used by the admin module to manage staff members.
 */
public class Staff {
    private final String id;
    private final String name;
    private Role role;

    public Staff(String id, String name) {
        this.id = id;
        this.name = name;
        this.role = Role.NONE;
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
        this.role = role;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                '}';
    }
}
