package application.repository;

import application.model.Customer;
import application.singleton.DatabaseConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryCustomerRepository implements CustomerRepository {
    private static final String TABLE_NAME = "customers";
    private final DatabaseConnection connection;

    public InMemoryCustomerRepository(DatabaseConnection connection) {
        this.connection = connection;
    }

    private List<Customer> table() {
        return connection.getTable(TABLE_NAME);
    }

    @Override
    public void save(Customer customer) {
        List<Customer> customers = table();
        customers.removeIf(existing -> existing.getId().equals(customer.getId()));
        customers.add(customer);
    }

    @Override
    public Optional<Customer> findById(String id) {
        return table().stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(table());
    }
}
