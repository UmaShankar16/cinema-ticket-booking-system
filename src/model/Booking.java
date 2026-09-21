package model;

import java.util.ArrayList;
import java.util.List;

public class Booking {
    private User user;
    private Screen screen;
    private Show show;
    private List<Seat> seats;

    public Booking(User user,Show show,Screen screen,List<Seat> seats)
    {
        this.user=user;
        this.show=show;
        this.screen=screen;
        this.seats = seats;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public Screen getScreen() {
        return screen;
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
