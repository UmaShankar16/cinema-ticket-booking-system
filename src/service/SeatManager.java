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
    public boolean deleteSeat(Screen screen,int seatNo,String seatRow)
    {
        Seat foundSeat=null;
        for(Seat seat: screen.getSeats())
        {
            if(seat.getSeatNo()==seatNo)
            {
                if(seat.getRow().equalsIgnoreCase(seatRow))
                {
                    foundSeat=seat;
                    break;
                }
            }
        }
        if(foundSeat==null)
        {
            return false;
        }
        return seatDAO.deleteSeat(screen,seatNo,seatRow);
    }
    public boolean updateSeatNumber(Screen screen,int oldSeatNo,int newSeatNo, String row)
    {
        Seat foundSeat=null;
        Seat newFoundSeat=null;
        if(oldSeatNo==newSeatNo)
        {
            return false;
        }
        for(Seat seat: screen.getSeats())
        {
            if(seat.getSeatNo()==oldSeatNo)
            {
                if(seat.getRow().equalsIgnoreCase(row))
                {
                    foundSeat=seat;
                }
            }
            if(seat.getSeatNo()==newSeatNo)
            {
                if(seat.getRow().equalsIgnoreCase(row))
                {
                    newFoundSeat=seat;
                }
            }
        }
        if(foundSeat==null)
        {
            return false;
        }
        if(newFoundSeat!=null)
        {
            return false;
        }
        return seatDAO.updateSeatNo(screen,oldSeatNo,newSeatNo,row);
    }
    public boolean updateSeatByRow(Screen screen,String oldSeatRow,String newSeatRow)
    {
        Seat foundSeat=null;
        Seat newFoundSeat=null;
        if(oldSeatRow.equalsIgnoreCase(newSeatRow))
        {
            return false;
        }
        for(Seat seat: screen.getSeats())
        {
            if(seat.getRow().equalsIgnoreCase(oldSeatRow))
            {
                foundSeat=seat;
            }
            if(seat.getRow().equalsIgnoreCase(newSeatRow))
            {
                newFoundSeat=seat;
            }
        }
        if(foundSeat==null)
        {
            return false;
        }
        if(newFoundSeat!=null)
        {
            return false;
        }
        return seatDAO.updateSeatRow(screen,oldSeatRow,newSeatRow);
    }
    public boolean updateSeatByType(Screen screen,int seatNo,String newType,String oldType, String row)
    {
        Seat foundSeat=null;
        if(oldType.equalsIgnoreCase(newType))
        {
            return false;
        }
        for(Seat seat: screen.getSeats())
        {
            if(seat.getSeatNo()==seatNo)
            {
                if(seat.getRow().equalsIgnoreCase(row))
                {
                    if(seat.getType().equalsIgnoreCase(oldType)) {
                        foundSeat = seat;
                    }
                }
            }
        }
        if(foundSeat==null)
        {
            return false;
        }
        return seatDAO.updateSeatType(screen,seatNo,newType,oldType,row);
    }
    public ArrayList<Seat> getAllSeat(Screen screen)
    {
        return seatDAO.getAllSeat(screen);
    }
}
