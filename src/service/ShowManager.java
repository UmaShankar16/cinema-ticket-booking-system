package service;

import model.Screen;
import model.Show;

import java.time.LocalDate;
import java.time.LocalTime;

public class ShowManager {

    public boolean addShow(Screen screen, Show show)
    {
        return screen.getShows().add(show);
    }

    public boolean deleteShow(Screen screen, String movieTitle, LocalDate date, LocalTime time)
    {
        Show foundShow=null;
        for(Show show: screen.getShows())
        {
            if(show.getMovie().equalsIgnoreCase(movieTitle))
            {
                if(show.getDate().equals(date))
                {
                    if(show.getStartTime().equals(time))
                    {
                        foundShow=show;
                        break;
                    }
                }
            }
        }
        if(foundShow==null)
        {
            return false;
        }
        screen.getShows().remove(foundShow);
        return true;
    }

    public boolean updateShowMovie(Screen screen,String oldMovieTitle,String newMovieTitle,LocalDate date,LocalTime time)
    {
        Show foundShow=null;
        if(oldMovieTitle.equalsIgnoreCase(newMovieTitle))
        {
            return false;
        }
        for(Show show: screen.getShows())
        {
            if(show.getMovie().equalsIgnoreCase(oldMovieTitle))
            {
                if (show.getDate().equals(date))
                {
                    if(show.getStartTime().equals(time))
                    {
                        foundShow=show;
                        break;
                    }
                }
            }
        }
        if(foundShow==null)
        {
            return false;
        }
        foundShow.setMovie(newMovieTitle);
        return true;
    }
    public boolean updateShowDate(Screen screen,String movieTitle,LocalDate newDate,LocalDate oldDate,LocalTime time)
    {
        Show foundShow=null;
        if(oldDate.equals(newDate))
        {
            return false;
        }
        for(Show show: screen.getShows())
        {
            if(show.getDate().equals(oldDate))
            {
                if (show.getMovie().equalsIgnoreCase(movieTitle))
                {
                    if(show.getStartTime().equals(time))
                    {
                        foundShow=show;
                        break;
                    }
                }
            }
        }
        if(foundShow==null)
        {
            return false;
        }
        foundShow.setDate(newDate);
        return true;
    }
    public boolean updateShowTime(Screen screen,String movieTitle,LocalTime newTime,LocalDate date,LocalTime oldTime)
    {
        Show foundShow=null;
        if(oldTime.equals(newTime))
        {
            return false;
        }
        for(Show show: screen.getShows())
        {
            if(show.getStartTime().equals(oldTime))
            {
                if (show.getDate().equals(date))
                {
                    if(show.getMovie().equalsIgnoreCase(movieTitle))
                    {
                        foundShow=show;
                        break;
                    }
                }
            }
        }
        if(foundShow==null)
        {
            return false;
        }
        foundShow.setStartTime(newTime);
        return true;
    }
}
