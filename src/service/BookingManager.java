package service;

import model.Booking;
import model.Seat;
import model.Show;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class BookingManager {
    private List<Booking> bookings;
    private SeatBookingManager seatBookingManager;

    public BookingManager()
    {
        bookings=new ArrayList<>();
        seatBookingManager = new SeatBookingManager();
    }

    public boolean addBooking(Booking booking)
    {
        if(seatBookingManager.bookSeats(booking.getShow(),booking.getSeats()))
        {
            return bookings.add(booking);
        }
        return false;
    }

    public List<Booking> getBookings(User user) {
        List<Booking> foundBooking = new ArrayList<>();
        for(Booking booking : bookings)
        {
            if(booking.getUser().getName().equalsIgnoreCase(user.getName())
            && booking.getUser().getPhone().equals(user.getPhone()))
            {
                foundBooking.add(booking);
            }
        }
        return foundBooking;
    }
    public List<Seat> getBookedSeats(Show show)
    {
        return seatBookingManager.getBookedSeats(show);
    }
}
