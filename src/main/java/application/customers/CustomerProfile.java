package application.customers;

/**
 * Represents a customer profile created via the Factory Method pattern.
 */
public interface CustomerProfile {
    String name();

    CustomerTier tier();

    String benefitsDescription();
}
