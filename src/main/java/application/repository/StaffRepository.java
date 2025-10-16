package application.repository;

import application.model.Staff;

import java.util.List;
import java.util.Optional;

public interface StaffRepository {
    void save(Staff staff);

    Optional<Staff> findById(String id);

    List<Staff> findAll();
}
