package application.customer;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Singleton registry storing customers to be reused throughout the system.
 */
public class CustomerRegistry {
    private static final CustomerRegistry INSTANCE = new CustomerRegistry();

    private final Map<String, Customer> customers = new LinkedHashMap<>();

    private CustomerRegistry() {
    }

    public static CustomerRegistry getInstance() {
        return INSTANCE;
    }

    public void register(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    public Customer findById(String id) {
        return customers.get(id);
    }

    public Collection<Customer> listCustomers() {
        return Collections.unmodifiableCollection(customers.values());
    }

    public void updateEmail(String id, String newEmail) {
        Customer existing = customers.get(id);
        if (existing == null) {
            throw new IllegalArgumentException("No customer found with id " + id);
        }
        customers.put(id, new Customer(id, existing.getName(), newEmail));
    }

    public void clear() {
        customers.clear();
    }
}
