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
        return cinemaHallDAO.updateCinemaHallLocation(hall,newLocation);
    }
    public boolean updateCinemaHallName(CinemaHall hall,String newName)
    {
        return cinemaHallDAO.updateCinemaHallName(hall,newName);
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
