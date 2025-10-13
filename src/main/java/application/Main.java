package application;

import application.admin.AdminService;
import application.admin.Role;
import application.admin.command.AdminCommandInvoker;
import application.admin.command.AssignRoleCommand;
import application.admin.command.CreateStaffCommand;
import application.admin.command.GenerateStaffRosterCommand;
import application.bookings.BookingService;
import application.bookings.LoyaltyDiscountStrategy;
import application.bookings.PeakSeasonPricingStrategy;
import application.promotions.EmailSubscriber;
import application.promotions.MobileAppSubscriber;
import application.promotions.PromotionEvent;
import application.promotions.PromotionManager;
import application.rooms.Room;
import application.rooms.RoomInventory;
import application.rooms.RoomType;
import application.customers.CustomerProfile;
import application.customers.CustomerProfileFactory;
import application.customers.CustomerTier;
import application.reviews.InMemoryReviewProcessor;
import application.reviews.ReviewProcessor;

import java.time.LocalDate;

/**
 * Demonstrates the hotel reservation subsystems and their design patterns.
 */
public class Main {

    public static void main(String[] args) {
        demonstrateAdminCommands();
        demonstrateRoomBuilder();
        demonstrateBookingStrategy();
        demonstratePromotionObserver();
        demonstrateCustomerFactory();
        demonstrateReviewTemplate();
    }

    private static void demonstrateAdminCommands() {
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
    }

    private static void demonstrateRoomBuilder() {
        RoomInventory inventory = new RoomInventory();
        Room deluxeSuite = new Room.Builder(501, RoomType.SUITE)
                .seaView()
                .balcony()
                .build();
        Room standardRoom = new Room.Builder(203, RoomType.STANDARD)
                .smokingAllowed()
                .build();

        inventory.addRoom(deluxeSuite);
        inventory.addRoom(standardRoom);

        System.out.println("\nRooms in inventory:");
        inventory.listRooms().forEach(System.out::println);
    }

    private static void demonstrateBookingStrategy() {
        Room sampleRoom = new Room.Builder(101, RoomType.DELUXE).build();
        BookingService bookingService = new BookingService();
        bookingService.setPricingStrategy(new PeakSeasonPricingStrategy());
        bookingService.createBooking("Charlie Diaz", sampleRoom, LocalDate.of(2024, 11, 12), 3, 180.0);

        bookingService.setPricingStrategy(new LoyaltyDiscountStrategy(0.15));
        bookingService.createBooking("Dana Lee", sampleRoom, LocalDate.of(2024, 12, 4), 2, 180.0);

        System.out.println("\nBookings:");
        bookingService.listBookings().forEach(booking ->
                System.out.printf("%s staying %d nights in room %d. Total: $%.2f%n",
                        booking.guestName(), booking.nights(), booking.room().getNumber(), booking.totalPrice()));
    }

    private static void demonstratePromotionObserver() {
        PromotionManager promotionManager = new PromotionManager();
        promotionManager.subscribe(new EmailSubscriber("guest@example.com"));
        promotionManager.subscribe(new MobileAppSubscriber("device-token-123"));

        System.out.println("\nSending promotion notifications:");
        promotionManager.announcePromotion(new PromotionEvent(
                "WINTER25",
                "Winter special - 25% off suites",
                25));
    }

    private static void demonstrateCustomerFactory() {
        CustomerProfileFactory factory = new CustomerProfileFactory();
        CustomerProfile regular = factory.createProfile("Ethan Fox", CustomerTier.REGULAR);
        CustomerProfile vip = factory.createProfile("Farah Green", CustomerTier.VIP);

        System.out.println("\nCustomer profiles:");
        for (CustomerProfile profile : new CustomerProfile[]{regular, vip}) {
            System.out.printf("%s (%s): %s%n", profile.name(), profile.tier(), profile.benefitsDescription());
        }
    }

    private static void demonstrateReviewTemplate() {
        ReviewProcessor processor = new InMemoryReviewProcessor();
        processor.publishReview("Grace Hill", 5, "Loved the stay! ");
        ReviewProcessor.PublishedReview review = processor.publishReview("Ivan Jones", 4, "Nice service.");

        System.out.println("\nLatest review by " + review.guestName() + " at rating " + review.rating());
    }
}
