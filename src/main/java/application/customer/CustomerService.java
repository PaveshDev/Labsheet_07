package application.customer;

import java.util.Collection;
import java.util.Locale;
import java.util.Objects;

/**
 * Provides higher level customer management operations that wrap the
 * underlying {@link CustomerRegistry}.
 */
public class CustomerService {
    private final CustomerRegistry registry;

    public CustomerService() {
        this(CustomerRegistry.getInstance());
    }

    public CustomerService(CustomerRegistry registry) {
        this.registry = Objects.requireNonNull(registry, "registry");
    }

    public Customer registerCustomer(String id, String name, String email) {
        String sanitizedId = sanitize("Customer id", id);
        String sanitizedName = sanitize("Customer name", name);
        String sanitizedEmail = sanitizeEmail(email);

        ensureUniqueId(sanitizedId);
        ensureUniqueEmail(sanitizedEmail, null);

        Customer customer = new Customer(sanitizedId, sanitizedName, sanitizedEmail);
        registry.register(customer);
        return customer;
    }

    public Customer updateContactDetails(String id, String newName, String newEmail) {
        Customer existing = requireCustomer(id);

        String updatedName = newName == null ? existing.getName() : sanitize("Customer name", newName);
        String updatedEmail = newEmail == null ? existing.getEmail() : sanitizeEmail(newEmail);

        if (!existing.getEmail().equalsIgnoreCase(updatedEmail)) {
            ensureUniqueEmail(updatedEmail, existing.getId());
        }

        Customer updated = new Customer(existing.getId(), updatedName, updatedEmail);
        registry.register(updated);
        return updated;
    }

    public Customer removeCustomer(String id) {
        String sanitizedId = sanitize("Customer id", id);
        Customer removed = registry.remove(sanitizedId);
        if (removed == null) {
            throw new IllegalArgumentException("Customer " + sanitizedId + " is not registered");
        }
        return removed;
    }

    public Customer viewCustomer(String id) {
        return requireCustomer(id);
    }

    public Collection<Customer> listCustomers() {
        return registry.listCustomers();
    }

    private Customer requireCustomer(String id) {
        String sanitizedId = sanitize("Customer id", id);
        Customer customer = registry.findById(sanitizedId);
        if (customer == null) {
            throw new IllegalArgumentException("Customer " + sanitizedId + " is not registered");
        }
        return customer;
    }

    private void ensureUniqueId(String id) {
        if (registry.findById(id) != null) {
            throw new IllegalArgumentException("Customer id '" + id + "' already exists");
        }
    }

    private void ensureUniqueEmail(String email, String currentCustomerId) {
        boolean emailInUse = registry.listCustomers().stream()
                .anyMatch(customer -> {
                    if (currentCustomerId != null && customer.getId().equals(currentCustomerId)) {
                        return false;
                    }
                    return customer.getEmail().equalsIgnoreCase(email);
                });
        if (emailInUse) {
            throw new IllegalArgumentException("Email address '" + email + "' is already registered");
        }
    }

    private String sanitize(String fieldName, String value) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " must not be null");
        }
        String trimmed = value.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }
        return trimmed;
    }

    private String sanitizeEmail(String email) {
        String sanitized = sanitize("Customer email", email).toLowerCase(Locale.ROOT);
        if (!sanitized.contains("@") || sanitized.startsWith("@") || sanitized.endsWith("@")) {
            throw new IllegalArgumentException("Email address '" + email + "' is invalid");
        }
        return sanitized;
    }
}
