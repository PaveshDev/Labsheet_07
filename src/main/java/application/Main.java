package application;

import application.controller.AdminController;
import application.controller.BookingController;
import application.controller.CustomerController;
import application.controller.PromotionController;
import application.controller.ReviewController;
import application.controller.RoomController;
import application.model.Customer;
import application.model.Role;
import application.model.Review;
import application.model.Room;
import application.observer.AdminReviewListener;
import application.observer.LoyaltyTeamListener;
import application.observer.ReviewPublisher;
import application.repository.BookingRepository;
import application.repository.CustomerRepository;
import application.repository.InMemoryBookingRepository;
import application.repository.InMemoryCustomerRepository;
import application.repository.InMemoryRoomRepository;
import application.repository.InMemoryStaffRepository;
import application.repository.RoomRepository;
import application.repository.StaffRepository;
import application.service.AdminService;
import application.service.BookingService;
import application.service.CustomerService;
import application.service.PromotionService;
import application.service.ReviewService;
import application.service.RoomService;
import application.singleton.DatabaseConnection;
import application.view.AdminView;
import application.view.BookingView;
import application.view.CustomerView;
import application.view.PromotionView;
import application.view.ReviewView;
import application.view.RoomView;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection connection = DatabaseConnection.getInstance();

        CustomerRepository customerRepository = new InMemoryCustomerRepository(connection);
        RoomRepository roomRepository = new InMemoryRoomRepository(connection);
        BookingRepository bookingRepository = new InMemoryBookingRepository(connection);
        StaffRepository staffRepository = new InMemoryStaffRepository(connection);

        CustomerService customerService = new CustomerService(customerRepository);
        RoomService roomService = new RoomService(roomRepository);
        PromotionService promotionService = new PromotionService();
        BookingService bookingService = new BookingService(bookingRepository, customerRepository, roomRepository);
        AdminService adminService = new AdminService(staffRepository);

        ReviewPublisher reviewPublisher = new ReviewPublisher();
        reviewPublisher.register(new AdminReviewListener());
        reviewPublisher.register(new LoyaltyTeamListener());
        ReviewService reviewService = new ReviewService(reviewPublisher);

        AdminController adminController = new AdminController(adminService, new AdminView());
        CustomerController customerController = new CustomerController(customerService, new CustomerView());
        RoomController roomController = new RoomController(roomService, new RoomView());
        PromotionController promotionController = new PromotionController(promotionService, new PromotionView());
        BookingController bookingController = new BookingController(bookingService, new BookingView());
        ReviewController reviewController = new ReviewController(reviewService, new ReviewView());

        adminController.createStaff("S001", "Mia");
        adminController.createStaff("S002", "Leo");
        adminController.assignRole("S001", Role.MANAGER);
        adminController.assignRole("S002", Role.RECEPTIONIST);
        adminController.displayStaff();

        customerController.registerCustomer(new Customer("C001", "Alice", true));
        customerController.registerCustomer(new Customer("C002", "Bob", false));
        customerController.displayCustomers();

        roomController.addRoom(new Room("101", "Single", 80));
        roomController.addRoom(new Room("202", "Suite", 150));
        roomController.displayRooms();

        promotionController.createPromotion("percentage");

        bookingController.createBooking("B001", "C001", "101", 3, LocalDate.now(), "percentage");
        bookingController.createBooking("B002", "C002", "202", 2, LocalDate.now().plusDays(7), "flat");
        bookingController.displayBookings();

        reviewController.submitReview(new Review("Alice", "Amazing stay!"));
    }
}
