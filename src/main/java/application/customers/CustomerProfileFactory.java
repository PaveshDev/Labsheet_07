package application.customers;

/**
 * Factory for creating customer profiles based on the requested tier.
 */
public class CustomerProfileFactory {
    public CustomerProfile createProfile(String name, CustomerTier tier) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Customer name must not be blank");
        }
        if (tier == null) {
            throw new IllegalArgumentException("Customer tier must not be null");
        }
        return switch (tier) {
            case REGULAR -> new RegularCustomer(name);
            case VIP -> new VipCustomer(name);
            case CORPORATE -> new CorporateCustomer(name);
        };
    }
}
