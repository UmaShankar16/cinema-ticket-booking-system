package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Show {
    private int showId;
    private int movieId;
    private int screenId;
    private LocalDate date;
    private LocalTime startTime;

    public Show(int movieId,int screenId,LocalDate date,LocalTime startTime)
    {
        this.screenId = screenId;
        this.movieId=movieId;
        this.date=date;
        this.startTime=startTime;
    }

    public void setScreenId(int screenId) {
        this.screenId = screenId;
    }

    public int getScreenId() {
        return screenId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public int getShowId() {
        return showId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getMovieId() {
        return movieId;
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
