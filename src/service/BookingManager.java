package service;

import DAO.BookingDAO;
import model.Booking;
import model.Show;
import model.User;

import java.util.List;
import java.util.Set;

public class BookingManager {
    private BookingDAO bookingDAO;

    public BookingManager(){
        bookingDAO = new BookingDAO();
    }

    public boolean addBooking(Booking booking){
        return bookingDAO.addBooking(booking);
    }
    public List<Booking> getBookingsByUser(User user) {
        return bookingDAO.getBookingsByUser(user);
    }
    public Set<Integer> getBookedSeatIdsByShow(int showId)
    {
        return bookingDAO.getBookedSeatIdsByShow(showId);
    }
    public List<Booking> getBookingsByShow(Show show)
    {
        return bookingDAO.getBookingByShow(show.getShowId());
    }
}
