package application.customers;

final class VipCustomer implements CustomerProfile {
    private final String name;

    VipCustomer(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public CustomerTier tier() {
        return CustomerTier.VIP;
    }

    @Override
    public String benefitsDescription() {
        return "Complimentary upgrades and lounge access.";
    }
}
