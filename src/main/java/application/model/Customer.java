package application.model;

import java.util.Objects;

/**
 * Customer entity used across repositories and services.
 */
public class Customer {
    private final String id;
    private final String name;
    private final boolean loyaltyMember;

    public Customer(String id, String name, boolean loyaltyMember) {
        this.id = Objects.requireNonNull(id, "id required");
        this.name = Objects.requireNonNull(name, "name required");
        this.loyaltyMember = loyaltyMember;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isLoyaltyMember() {
        return loyaltyMember;
    }

    @Override
    public String toString() {
        return name + (loyaltyMember ? " (Loyalty)" : "");
    }
}
