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

    public boolean addShow(Screen screen, Show show)
    {
        return showDAO.addShow(screen,show);
    }
    public boolean deleteShow(Screen screen,Show show)
    {
        return showDAO.deleteShow(screen,show);
    }
    public boolean updateShowDate(Screen screen,Show show,LocalDate newDate)
    {
        if(show.getDate().equals(newDate))
        {
            return false;
        }
        return showDAO.updateShowDate(screen,show,newDate);
    }
    public boolean updateShowTime(Screen screen,Show show,LocalTime newTime)
    {
        if(show.getStartTime().equals(newTime))
        {
            return false;
        }
        return showDAO.updateShowTime(screen,show,newTime);
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
