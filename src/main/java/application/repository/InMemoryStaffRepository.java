package application.repository;

import application.model.Staff;
import application.singleton.DatabaseConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryStaffRepository implements StaffRepository {
    private static final String TABLE_NAME = "staff";
    private final DatabaseConnection connection;

    public InMemoryStaffRepository(DatabaseConnection connection) {
        this.connection = connection;
    }

    private List<Staff> table() {
        return connection.getTable(TABLE_NAME);
    }

    @Override
    public void save(Staff staff) {
        List<Staff> staffTable = table();
        staffTable.removeIf(existing -> existing.getId().equals(staff.getId()));
        staffTable.add(staff);
    }

    @Override
    public Optional<Staff> findById(String id) {
        return table().stream().filter(staff -> staff.getId().equals(id)).findFirst();
    }

    @Override
    public List<Staff> findAll() {
        return new ArrayList<>(table());
    }
}
