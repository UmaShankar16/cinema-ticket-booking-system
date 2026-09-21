package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Show {
    private String movie;
    private LocalDate date;
    private LocalTime startTime;

    public Show(String movie,LocalDate date,LocalTime startTime)
    {
        this.movie=movie;
        this.date=date;
        this.startTime=startTime;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getMovie() {
        return movie;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }
}
