package model;

import java.util.ArrayList;
import java.util.List;

public class Screen {
    private int screenNo;
    private List<Seat> seats;
    private List<Show> shows;

    public Screen(int screenNo)
    {
        this.screenNo=screenNo;
        this.seats=new ArrayList<>();
        this.shows=new ArrayList<>();
    }

    public void setScreenNo(int screenNo) {
        this.screenNo = screenNo;
    }

    public int getScreenNo() {
        return screenNo;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }
    public List<Show> getShows() {
        return shows;
    }
}
