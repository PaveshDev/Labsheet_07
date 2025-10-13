package application.customers;

final class RegularCustomer implements CustomerProfile {
    private final String name;

    RegularCustomer(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public CustomerTier tier() {
        return CustomerTier.REGULAR;
    }

    @Override
    public String benefitsDescription() {
        return "Access to standard room rates.";
    }
}
