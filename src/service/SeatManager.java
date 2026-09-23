package service;

import model.Screen;
import model.Seat;

public class SeatManager {

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
        return screen.getSeats().add(seat);
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
        return screen.getSeats().remove(foundSeat);
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
        foundSeat.setSeatNo(newSeatNo);
        return true;
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
                break;
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
        foundSeat.setRow(newSeatRow);
        return true;
    }
    public boolean updateSeatByType(Screen screen,int SeatNo,String newType,String oldType, String row)
    {
        Seat foundSeat=null;
        if(oldType.equalsIgnoreCase(newType))
        {
            return false;
        }
        for(Seat seat: screen.getSeats())
        {
            if(seat.getSeatNo()==SeatNo)
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
        foundSeat.setType(newType);
        return true;
    }
}
