package application.repository;

import application.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    void save(Customer customer);

    Optional<Customer> findById(String id);

    List<Customer> findAll();
}
