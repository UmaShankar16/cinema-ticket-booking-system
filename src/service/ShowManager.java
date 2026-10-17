package service;

import DAO.ShowDAO;
import model.Screen;
import model.Show;
import model.Movie;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ShowManager {

    private ShowDAO showDAO;
    public ShowManager()
    {
        showDAO = new ShowDAO();
    }

    public boolean addShow(Show show)
    {
        return showDAO.addShow(show);
    }
    public boolean deleteShow(Show show)
    {
        return showDAO.deleteShow(show);
    }
    public boolean updateShowDate(Show show,LocalDate newDate)
    {
        if(show.getDate().equals(newDate))
        {
            return false;
        }
        boolean updated = showDAO.updateShowDate(show,newDate);
        if(updated)
        {
            show.setDate(newDate);
        }
        return updated;
    }
    public boolean updateShowTime(Show show,LocalTime newTime)
    {
        if(show.getStartTime().equals(newTime))
        {
            return false;
        }
        boolean updated = showDAO.updateShowTime(show,newTime);
        if(updated)
        {
            show.setStartTime(newTime);
        }
        return updated;
    }
    public ArrayList<Show> getAllShowsByScreen(Screen screen)
    {
        ArrayList<Show> foundShows = showDAO.getAllShowsByScreen(screen);
        return foundShows;
    }
    public ArrayList<Show> getAllShowsByMovie(Movie movie)
    {
        ArrayList<Show> foundShows = showDAO.getAllShowsByMovie(movie);
        return foundShows;
    }
    public ArrayList<Show> getAllShowsByDate(LocalDate date)
    {
        ArrayList<Show> foundShows = showDAO.getAllShowsByDate(date);
        return foundShows;
    }
    public ArrayList<Show> getAllShows()
    {
        ArrayList<Show> foundShows = showDAO.getAllShows();
        return foundShows;
    }
}
