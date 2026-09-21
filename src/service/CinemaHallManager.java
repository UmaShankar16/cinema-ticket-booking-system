package service;

import model.CinemaHall;

import java.util.ArrayList;
import java.util.List;

public class CinemaHallManager {
    private List<CinemaHall> cinemaHalls;

    public CinemaHallManager()
    {
        cinemaHalls = new ArrayList<>();
    }

    public List<CinemaHall> getCinemaHalls() {
        return cinemaHalls;
    }

    public boolean addCinemaHall(CinemaHall cinemaHall)
    {
        return cinemaHalls.add(cinemaHall);
    }
    public boolean deleteCinemaHall(CinemaHall cinemaHall)
    {
        CinemaHall foundHall = null;
        for(CinemaHall hall : cinemaHalls)
        {
            if(hall.getCinemaHallName().equalsIgnoreCase(cinemaHall.getCinemaHallName()))
            {
                if (hall.getLocation().equalsIgnoreCase(cinemaHall.getLocation()))
                {
                    foundHall=hall;
                    break;
                }
            }
        }
        if(foundHall==null)
        {
            return false;
        }
        cinemaHalls.remove(foundHall);
        return true;
    }
    public boolean updateCinemaHallLocation(String name,String newLocation)
    {
        CinemaHall foundHall = null;
        for(CinemaHall hall : cinemaHalls)
        {
            if(hall.getCinemaHallName().equalsIgnoreCase(name))
            {
                foundHall=hall;
                break;
            }
        }
        if(foundHall==null)
        {
            return false;
        }
        foundHall.setLocation(newLocation);
        return true;
    }
    public boolean updateCinemaHallName(String oldName,String newName)
    {
        CinemaHall foundHall = null;
        for(CinemaHall hall : cinemaHalls)
        {
            if(hall.getCinemaHallName().equalsIgnoreCase(oldName))
            {
                foundHall=hall;
                break;
            }
        }
        if(foundHall==null)
        {
            return false;
        }
        foundHall.setCinemaHallName(newName);
        return true;
    }
    public List<CinemaHall> searchByHallName(String hallName)
    {
        List<CinemaHall> searchedHall = new ArrayList<>();
        for(CinemaHall hall: cinemaHalls)
        {
            if(hall.getCinemaHallName().equalsIgnoreCase(hallName))
            {
                searchedHall.add(hall);
            }
        }
        return searchedHall;
    }
    public List<CinemaHall> searchByLocation(String location)
    {
        List<CinemaHall> searchedHall = new ArrayList<>();
        for(CinemaHall hall: cinemaHalls)
        {
            if(hall.getLocation().equalsIgnoreCase(location))
            {
                searchedHall.add(hall);
            }
        }
        return searchedHall;
    }
    public List<CinemaHall> searchByNameAndLocation(String hallName,String location)
    {
        List<CinemaHall> searchedHall = new ArrayList<>();
        for(CinemaHall hall : cinemaHalls)
        {
            if(hall.getCinemaHallName().equalsIgnoreCase(hallName))
            {
                if(hall.getLocation().equalsIgnoreCase(location))
                {
                    searchedHall.add(hall);
                }
            }
        }
        return searchedHall;
    }
}
