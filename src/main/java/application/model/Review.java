package application.model;

/**
 * Simple value object representing a review left by a customer.
 */
public class Review {
    private final String customerName;
    private final String comment;

    public Review(String customerName, String comment) {
        this.customerName = customerName;
        this.comment = comment;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getComment() {
        return comment;
    }
}
