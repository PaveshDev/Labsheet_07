package application.customer;

import java.util.Collection;
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
        return customers.values();
    }
}
