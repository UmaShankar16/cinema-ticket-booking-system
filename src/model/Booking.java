package model;

import java.util.ArrayList;
import java.util.List;

public class Booking {
    private int bookingId;
    private User user;
    private Show show;
    private List<Seat> seats;

    public Booking(User user,Show show,List<Seat> seats)
    {
        this.user=user;
        this.show=show;
        this.seats = seats;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Show getShow() {
        return show;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public List<Seat> getSeats() {
        return seats;
    }
}
