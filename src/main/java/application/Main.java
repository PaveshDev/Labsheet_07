package application;

import application.admin.AdminService;
import application.admin.Role;
import application.admin.command.AdminCommandInvoker;
import application.admin.command.AssignRoleCommand;
import application.admin.command.CreateStaffCommand;
import application.admin.command.GenerateStaffRosterCommand;
import application.booking.Booking;
import application.booking.BookingService;
import application.booking.strategy.LoyaltyPricingStrategy;
import application.booking.strategy.StandardPricingStrategy;
import application.customer.Customer;
import application.customer.CustomerRegistry;
import application.promotion.Promotion;
import application.promotion.PromotionFactory;
import application.promotion.PromotionService;
import application.review.AdminReviewListener;
import application.review.LoyaltyTeamListener;
import application.review.Review;
import application.review.ReviewPublisher;
import application.room.Room;
import application.room.RoomCatalog;

import java.time.LocalDate;
import java.util.Set;

/**
 * Demonstrates the hotel reservation subsystems and the design patterns used
 * throughout the implementation.
 */
public class Main {

    public static void main(String[] args) {
        runAdminDemo();
        runRoomAndBookingDemo();
        runReviewDemo();
    }

    private static void runAdminDemo() {
        System.out.println("--- Admin Command Pattern Demo ---");
        AdminService adminService = new AdminService();
        AdminCommandInvoker invoker = new AdminCommandInvoker();

        CreateStaffCommand createReceptionist = new CreateStaffCommand(adminService, "Alice Johnson");
        CreateStaffCommand createHousekeeper = new CreateStaffCommand(adminService, "Ben King");

        invoker.register("create-alice", createReceptionist);
        invoker.register("create-ben", createHousekeeper);
        invoker.register("print-roster", new GenerateStaffRosterCommand(adminService));

        invoker.execute("create-alice");
        invoker.execute("create-ben");

        invoker.register("assign-alice", new AssignRoleCommand(
                adminService,
                createReceptionist.getCreatedStaff().getId(),
                Role.MANAGER));
        invoker.register("assign-ben", new AssignRoleCommand(
                adminService,
                createHousekeeper.getCreatedStaff().getId(),
                Role.HOUSEKEEPING));

        invoker.execute("assign-alice");
        invoker.execute("assign-ben");
        invoker.execute("print-roster");
        System.out.println();
    }

    private static void runRoomAndBookingDemo() {
        System.out.println("--- Room Builder, Strategy, and Factory Patterns Demo ---");

        RoomCatalog catalog = new RoomCatalog();
        Room deluxeSuite = new Room.Builder("501")
                .type("Deluxe Suite")
                .capacity(4)
                .nightlyRate(350)
                .addAmenity("Ocean view")
                .addAmenity("Mini bar")
                .build();
        Room familyRoom = new Room.Builder("205")
                .type("Family Room")
                .capacity(5)
                .nightlyRate(220)
                .addAmenity("Kitchenette")
                .build();

        catalog.register(deluxeSuite);
        catalog.register(familyRoom);
        catalog.listRooms().forEach(room -> System.out.println("Registered room: " + room));

        CustomerRegistry customerRegistry = CustomerRegistry.getInstance();
        customerRegistry.register(new Customer("CUST-100", "Grace Lee", "grace@example.com"));
        customerRegistry.register(new Customer("CUST-200", "Henry Fox", "henry@example.com"));

        BookingService bookingService = new BookingService();
        bookingService.registerStrategy("standard", new StandardPricingStrategy());
        bookingService.registerStrategy("loyalty", new LoyaltyPricingStrategy(0.15, 0.10, Set.of("CUST-100")));

        PromotionService promotionService = new PromotionService(new PromotionFactory());
        Promotion summerPromo = promotionService.createPromotion("percentage", "Summer Sale", 0.20);
        Promotion welcomePromo = promotionService.createPromotion("flat", "Welcome Credit", 50);

        Booking graceBooking = bookingService.createBooking(
                "loyalty",
                deluxeSuite,
                "CUST-100",
                LocalDate.now().plusDays(7),
                3);
        double discountedTotal = welcomePromo.apply(summerPromo.apply(graceBooking.getTotalCost()));
        System.out.printf("Booking %s final total after promotions: $%.2f%n", graceBooking.getBookingId(), discountedTotal);

        Booking henryBooking = bookingService.createBooking(
                "standard",
                familyRoom,
                "CUST-200",
                LocalDate.now().plusDays(3),
                2);
        System.out.printf("Booking %s standard total: $%.2f%n", henryBooking.getBookingId(), henryBooking.getTotalCost());
        System.out.println();
    }

    private static void runReviewDemo() {
        System.out.println("--- Review Observer Pattern Demo ---");
        ReviewPublisher publisher = new ReviewPublisher();
        AdminReviewListener adminListener = new AdminReviewListener();
        LoyaltyTeamListener loyaltyListener = new LoyaltyTeamListener();

        publisher.register(adminListener);
        publisher.register(loyaltyListener);

        publisher.publish(new Review("CUST-100", 5, "Loved the deluxe suite!"));
        publisher.publish(new Review("CUST-200", 3, "Great service but room was small."));

        System.out.println("Total reviews captured by admin: " + adminListener.getCapturedReviews().size());
        System.out.println("Positive reviews flagged for loyalty rewards: " + loyaltyListener.getPositiveReviews().size());
        System.out.println();
    }
}
