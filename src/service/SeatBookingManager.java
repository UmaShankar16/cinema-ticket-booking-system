package service;

import model.Seat;
import model.Show;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeatBookingManager {
    private Map<Show,List<Seat>> bookingSeats;

    public SeatBookingManager()
    {
        bookingSeats = new HashMap<>();
    }

    public boolean bookSeats(Show show,List<Seat> bookedSeats)
    {
        if(bookingSeats.containsKey(show))
        {
            List<Seat> existingSeats = bookingSeats.get(show);
            for(Seat newSeat : bookedSeats) //Refers to the new seats that user choose
            {
                for(Seat existingSeat : existingSeats) //Refers to the existing seats present in map
                {
                    if(newSeat.getSeatNo() == existingSeat.getSeatNo() &&
                            newSeat.getRow().equalsIgnoreCase(existingSeat.getRow()))
                    {
                        return false;
                    }
                }
            }
            existingSeats.addAll(bookedSeats);
            return true;
        }
        bookingSeats.put(show,bookedSeats);
        return true;
    }

    public List<Seat> getBookedSeats(Show show)
    {
        List<Seat> foundSeats = new ArrayList<>();
        if(bookingSeats.containsKey(show))
        {
            foundSeats = bookingSeats.get(show);
        }
        return foundSeats;
    }
}
