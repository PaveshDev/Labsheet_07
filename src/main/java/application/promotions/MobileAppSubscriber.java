package application.promotions;

/**
 * Pushes promotion details to the mobile application.
 */
public class MobileAppSubscriber implements PromotionSubscriber {
    private final String deviceToken;

    public MobileAppSubscriber(String deviceToken) {
        if (deviceToken == null || deviceToken.isBlank()) {
            throw new IllegalArgumentException("Device token must not be blank");
        }
        this.deviceToken = deviceToken;
    }

    @Override
    public void onPromotion(PromotionEvent event) {
        System.out.println("Push notification to " + deviceToken + ": " + event.description());
    }
}
