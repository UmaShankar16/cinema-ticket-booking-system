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
    public boolean updateCinemaHallLocation(CinemaHall hall,String newLocation)
    {
        CinemaHall foundHall = null;
        for(CinemaHall hall1 : cinemaHalls)
        {
            if(hall1.getCinemaHallName().equalsIgnoreCase(hall.getCinemaHallName()))
            {
                if(hall1.getLocation().equalsIgnoreCase(hall.getLocation()))
                {
                    foundHall=hall1;
                    break;
                }
            }
        }
        if(foundHall==null)
        {
            return false;
        }
        foundHall.setLocation(newLocation);
        return true;
    }
    public boolean updateCinemaHallName(CinemaHall hall,String newName)
    {
        CinemaHall foundHall = null;
        for(CinemaHall hall1 : cinemaHalls)
        {
            if(hall1.getCinemaHallName().equalsIgnoreCase(hall.getCinemaHallName()))
            {
                if(hall1.getLocation().equalsIgnoreCase(hall.getLocation()))
                {
                    foundHall=hall1;
                    break;
                }
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
    public List<CinemaHall> searchByNameAndLocation(CinemaHall hall)
    {
        List<CinemaHall> searchedHall = new ArrayList<>();
        for(CinemaHall hall1 : cinemaHalls)
        {
            if(hall1.getCinemaHallName().equalsIgnoreCase(hall.getCinemaHallName()))
            {
                if(hall1.getLocation().equalsIgnoreCase(hall.getLocation()))
                {
                    searchedHall.add(hall);
                }
            }
        }
        return searchedHall;
    }
}
