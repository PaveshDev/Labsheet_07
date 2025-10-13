package application.customers;

final class CorporateCustomer implements CustomerProfile {
    private final String name;

    CorporateCustomer(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public CustomerTier tier() {
        return CustomerTier.CORPORATE;
    }

    @Override
    public String benefitsDescription() {
        return "Negotiated corporate rates and invoicing.";
    }
}
