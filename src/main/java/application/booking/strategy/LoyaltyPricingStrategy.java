package application.booking.strategy;

import application.room.Room;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

/**
 * Strategy applying a loyalty discount plus a weekend surcharge to illustrate
 * pluggable pricing rules.
 */
public class LoyaltyPricingStrategy implements PricingStrategy {
    private final double loyaltyDiscount;
    private final double weekendSurcharge;
    private final Set<String> loyaltyCustomerIds;

    public LoyaltyPricingStrategy(double loyaltyDiscount, double weekendSurcharge, Set<String> loyaltyCustomerIds) {
        this.loyaltyDiscount = loyaltyDiscount;
        this.weekendSurcharge = weekendSurcharge;
        this.loyaltyCustomerIds = Set.copyOf(loyaltyCustomerIds);
    }

    @Override
    public double calculateTotal(Room room, String customerId, LocalDate checkInDate, int nights) {
        double base = room.getNightlyRate() * nights;
        if (loyaltyCustomerIds.contains(customerId)) {
            base -= base * loyaltyDiscount;
        }
        if (fallsOnWeekend(checkInDate)) {
            base += base * weekendSurcharge;
        }
        return base;
    }

    private boolean fallsOnWeekend(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.FRIDAY || day == DayOfWeek.SATURDAY;
    }
}
