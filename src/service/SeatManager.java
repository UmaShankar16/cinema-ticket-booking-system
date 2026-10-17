package service;

import DAO.SeatDAO;
import model.Screen;
import model.Seat;

import java.util.ArrayList;

public class SeatManager {
    private SeatDAO seatDAO;
    public SeatManager()
    {
        seatDAO = new SeatDAO();
    }

    public boolean addSeat(Screen screen, Seat seat)
    {
        for(Seat existingSeat : screen.getSeats())
        {
            if(existingSeat.getSeatNo() == seat.getSeatNo() &&
            existingSeat.getRow().equalsIgnoreCase(seat.getRow()))
            {
                return false;
            }
        }
        return seatDAO.addSeat(screen,seat);
    }
    public boolean deleteSeat(Screen screen,Seat seat)
    {
        return seatDAO.deleteSeat(screen,seat);
    }
    public boolean updateSeatNumber(Screen screen,Seat seat,int newSeatNo)
    {
        if(seat.getSeatNo()==newSeatNo)
        {
            return false;
        }
        return seatDAO.updateSeatNo(screen,seat,newSeatNo);
    }
    public boolean updateSeatByRow(Screen screen,Seat seat,String newSeatRow)
    {
        if(seat.getRow().equalsIgnoreCase(newSeatRow))
        {
            return false;
        }
        return seatDAO.updateSeatRow(screen,seat,newSeatRow);
    }
    public boolean updateSeatByType(Screen screen,Seat seat,String newType)
    {
        if(seat.getType().equalsIgnoreCase(newType))
        {
            return false;
        }
        return seatDAO.updateSeatType(screen,seat,newType);
    }
    public ArrayList<Seat> getAllSeat(Screen screen)
    {
        return seatDAO.getAllSeat(screen);
    }
}
