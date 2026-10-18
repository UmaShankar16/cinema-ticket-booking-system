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
    public boolean deleteScreen(CinemaHall hall,Screen screen)
    {
        return screenDAO.deleteScreen(hall, screen);
    }
    public boolean updateScreen(CinemaHall hall,Screen screen,int newScreenNo)
    {
        if(newScreenNo == screen.getScreenNo())
        {
            return false;
        }
        return screenDAO.updateScreen(hall,screen,newScreenNo);
    }
    public ArrayList<Screen> getAllScreenOfCinemaHall(CinemaHall hall)
    {
        return screenDAO.getAllScreenOfCinemaHall(hall);
    }
}
