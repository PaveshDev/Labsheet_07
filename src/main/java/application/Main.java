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
import application.customer.CustomerService;
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

        CustomerService customerService = new CustomerService();
        PromotionService promotionService = new PromotionService(new PromotionFactory());

        runCustomerAndPromotionDemo(customerService, promotionService);
        runRoomAndBookingDemo(customerService, promotionService);
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

    private static void runCustomerAndPromotionDemo(CustomerService customerService, PromotionService promotionService) {
        System.out.println("--- Customer and Promotion Management Demo ---");

        Customer grace = customerService.registerCustomer("CUST-100", "Grace Lee", "grace@example.com");
        Customer henry = customerService.registerCustomer("CUST-200", "Henry Fox", "henry@example.com");

        customerService.updateContactDetails(henry.getId(), null, "henry.fox@example.com");

        System.out.println("Registered customers:");
        customerService.listCustomers().forEach(customer ->
                System.out.println("- " + customer.getId() + ": " + customer));

        promotionService.createPromotion("percentage", "Summer Sale", 0.20);
        promotionService.createPromotion("flat", "Welcome Credit", 50);

        System.out.println("Active promotions:");
        promotionService.listPromotions().forEach(promotion ->
                System.out.println("- " + promotion.name()));

        double sampleTotal = promotionService.applyPromotions(500, "Summer Sale", "Welcome Credit");
        System.out.printf("Sample total after promotions: $%.2f%n", sampleTotal);
        System.out.println();
    }

    private static void runRoomAndBookingDemo(CustomerService customerService, PromotionService promotionService) {
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

        BookingService bookingService = new BookingService();
        bookingService.registerStrategy("standard", new StandardPricingStrategy());
        bookingService.registerStrategy("loyalty", new LoyaltyPricingStrategy(0.15, 0.10, Set.of("CUST-100")));

        Booking graceBooking = bookingService.createBooking(
                "loyalty",
                deluxeSuite,
                "CUST-100",
                LocalDate.now().plusDays(7),
                3);
        double discountedTotal = promotionService.applyPromotions(
                graceBooking.getTotalCost(),
                "Summer Sale",
                "Welcome Credit");
        Customer graceCustomer = customerService.viewCustomer("CUST-100");
        System.out.printf(
                "Booking %s for %s final total after promotions: $%.2f%n",
                graceBooking.getBookingId(),
                graceCustomer.getName(),
                discountedTotal);

        Booking henryBooking = bookingService.createBooking(
                "standard",
                familyRoom,
                "CUST-200",
                LocalDate.now().plusDays(3),
                2);
        Customer henryCustomer = customerService.viewCustomer("CUST-200");
        System.out.printf(
                "Booking %s for %s standard total: $%.2f%n",
                henryBooking.getBookingId(),
                henryCustomer.getName(),
                henryBooking.getTotalCost());
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
