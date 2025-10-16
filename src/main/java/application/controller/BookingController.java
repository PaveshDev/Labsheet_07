package application.controller;

import application.model.Booking;
import application.service.BookingService;
import application.view.BookingView;

import java.time.LocalDate;

public class BookingController {
    private final BookingService bookingService;
    private final BookingView bookingView;

    public BookingController(BookingService bookingService, BookingView bookingView) {
        this.bookingService = bookingService;
        this.bookingView = bookingView;
    }

    public void createBooking(String bookingId,
                              String customerId,
                              String roomNumber,
                              int nights,
                              LocalDate checkIn,
                              String promotionType) {
        Booking booking = bookingService.createBooking(bookingId, customerId, roomNumber, nights, checkIn, promotionType);
        bookingView.showBookingCreated(booking);
    }

    public void displayBookings() {
        bookingView.showBookings(bookingService.listBookings());
    }
}
