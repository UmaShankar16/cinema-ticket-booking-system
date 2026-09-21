package service;

import model.CinemaHall;
import model.Screen;

public class ScreenManager {

    public boolean addScreen(CinemaHall hall,Screen screen)
    {
        return hall.getScreen().add(screen);
    }

    public boolean deleteScreen(CinemaHall hall,int screenNo)
    {
        Screen foundScreen = null;
        for(Screen screen : hall.getScreen())
        {
            if(screen.getScreenNo()==screenNo)
            {
                foundScreen=screen;
                break;
            }
        }
        if(foundScreen == null)
        {
            return false;
        }
        return hall.getScreen().remove(foundScreen);
    }
    public boolean updateScreen(CinemaHall hall,int oldScreenNo,int newScreenNo)
    {
        Screen foundScreen = null;
        Screen newFound=null;
        if(oldScreenNo==newScreenNo)
        {
            return false;
        }
        for(Screen screen : hall.getScreen())
        {
            if(screen.getScreenNo()==oldScreenNo)
            {
                foundScreen=screen;
            }
            if(screen.getScreenNo()==newScreenNo)
            {
                newFound=screen;
            }
        }
        if(foundScreen == null)
        {
            return false;
        }
        if(newFound != null)
        {
            return false;
        }
        foundScreen.setScreenNo(newScreenNo);
        return true;
    }
}
