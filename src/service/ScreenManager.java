package service;

import DAO.ScreenDAO;
import model.CinemaHall;
import model.Screen;

import java.util.ArrayList;

public class ScreenManager {

    private ScreenDAO screenDAO;
    public ScreenManager()
    {
        screenDAO = new ScreenDAO();
    }

    public boolean addScreen(CinemaHall hall,Screen screen)
    {
        return screenDAO.addScreen(hall,screen);
    }

    public boolean deleteScreen(CinemaHall hall,int screenNo)
    {
        return screenDAO.deleteScreen(hall, screenNo);
    }
    public boolean updateScreen(CinemaHall hall,int newScreenNo,int oldScreenNo)
    {
        return screenDAO.updateScreen(hall, newScreenNo, oldScreenNo);
    }
    public ArrayList<Screen> getAllScreenOfCinemaHall(CinemaHall hall)
    {
        return screenDAO.getAllScreenOfCinemaHall(hall);
    }
}
