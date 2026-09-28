package model;

import java.util.ArrayList;
import java.util.List;

public class CinemaHall {
    private int hallId;
    private String cinemaHallName;
    private String location;
    private List<Screen> screen;

    public CinemaHall(String cinemaHallName,String location)
    {
        this.cinemaHallName=cinemaHallName;
        this.location=location;
        screen = new ArrayList<>();
    }
    public void setHallId(int hallId)
    {
        this.hallId = hallId;
    }

    public int getHallId() {
        return hallId;
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
        this.screen = screenList;
    }

    public List<Screen> getScreen() {
        return screen;
    }
}
