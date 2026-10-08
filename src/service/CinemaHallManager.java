package service;

import DAO.CinemaHallDAO;
import model.CinemaHall;

import java.util.List;

public class CinemaHallManager {
    private CinemaHallDAO cinemaHallDAO;

    public CinemaHallManager()
    {
        cinemaHallDAO = new CinemaHallDAO();
    }

    public List<CinemaHall> getAllCinemaHalls() {
        return cinemaHallDAO.getAllCinemaHall();
    }

    public boolean addCinemaHall(CinemaHall cinemaHall)
    {
        return cinemaHallDAO.addCinemaHall(cinemaHall);
    }
    public boolean deleteCinemaHall(CinemaHall cinemaHall)
    {
        return cinemaHallDAO.deleteCinemaHall(cinemaHall);
    }
    public boolean updateCinemaHallLocation(CinemaHall hall,String newLocation)
    {
        if(hall.getLocation().equalsIgnoreCase(newLocation))
        {
            return false;
        }
        boolean updated = cinemaHallDAO.updateCinemaHallLocation(hall,newLocation);
        if(updated)
        {
            hall.setLocation(newLocation);
        }
        return updated;
    }
    public boolean updateCinemaHallName(CinemaHall hall,String newName)
    {
        if(hall.getCinemaHallName().equalsIgnoreCase(newName))
        {
            return false;
        }
        boolean updated = cinemaHallDAO.updateCinemaHallName(hall,newName);
        if(updated)
        {
            hall.setCinemaHallName(newName);
        }
        return updated;
    }
    public List<CinemaHall> searchByHallName(String hallName)
    {
        return cinemaHallDAO.searchCinemaHallByName(hallName);
    }
    public List<CinemaHall> searchByLocation(String location)
    {
        return cinemaHallDAO.searchCinemaHallByLocation(location);
    }
    public CinemaHall searchByNameAndLocation(CinemaHall hall)
    {
        return cinemaHallDAO.searchCinemaHallByNameAndLocation(hall);
    }
}
