package application.service;

import application.factory.PromotionFactory;
import application.model.Booking;
import application.model.Customer;
import application.model.Promotion;
import application.model.Room;
import application.repository.BookingRepository;
import application.repository.CustomerRepository;
import application.repository.RoomRepository;
import application.strategy.LoyaltyPricingStrategy;
import application.strategy.PricingStrategy;
import application.strategy.StandardPricingStrategy;

import java.time.LocalDate;
import java.util.List;

public class BookingService {
    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;

    private final PricingStrategy standardStrategy = new StandardPricingStrategy();
    private final PricingStrategy loyaltyStrategy = new LoyaltyPricingStrategy();

    public BookingService(BookingRepository bookingRepository,
                          CustomerRepository customerRepository,
                          RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.roomRepository = roomRepository;
    }

    public Booking createBooking(String bookingId,
                                 String customerId,
                                 String roomNumber,
                                 int nights,
                                 LocalDate checkIn,
                                 String promotionType) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + customerId));
        Room room = roomRepository.findByNumber(roomNumber)
                .orElseThrow(() -> new IllegalArgumentException("Room not found: " + roomNumber));

        PricingStrategy strategy = customer.isLoyaltyMember() ? loyaltyStrategy : standardStrategy;
        double basePrice = strategy.calculatePrice(room, nights);

        Promotion promotion = resolvePromotion(promotionType);
        double finalPrice = promotion != null ? promotion.apply(basePrice) : basePrice;

        Booking booking = new Booking.Builder(bookingId)
                .customer(customer)
                .room(room)
                .checkInDate(checkIn)
                .checkOutDate(checkIn.plusDays(nights))
                .totalPrice(finalPrice)
                .build();

        bookingRepository.save(booking);
        return booking;
    }

    public List<Booking> listBookings() {
        return bookingRepository.findAll();
    }

    private Promotion resolvePromotion(String promotionType) {
        if (promotionType == null || promotionType.isBlank() || "none".equalsIgnoreCase(promotionType)) {
            return null;
        }
        return PromotionFactory.createPromotion(promotionType);
    }
}
