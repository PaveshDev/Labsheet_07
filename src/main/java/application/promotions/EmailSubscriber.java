package application.promotions;

/**
 * Sends promotion details by email.
 */
public class EmailSubscriber implements PromotionSubscriber {
    private final String email;

    public EmailSubscriber(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email must not be blank");
        }
        this.email = email;
    }

    @Override
    public void onPromotion(PromotionEvent event) {
        System.out.println("Emailing " + email + ": " + event.description() + " (" + event.code() + ")");
    }
}
