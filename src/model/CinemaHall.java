package model;

import java.util.ArrayList;
import java.util.List;

public class CinemaHall {
    private String cinemaHallName;
    private String location;
    private List<Screen> screen;

    public CinemaHall(String cinemaHallName,String location)
    {
        this.cinemaHallName=cinemaHallName;
        this.location=location;
        screen = new ArrayList<>();
    }

    public void setCinemaHallName(String cinemaHallName) {
        this.cinemaHallName = cinemaHallName;
    }

    public String getCinemaHallName() {
        return cinemaHallName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setScreen(List<Screen> screenList) {
        this.screen = screen;
    }

    public List<Screen> getScreen() {
        return screen;
    }
}
