package DAO;

import DB.DBConnection;
import model.Booking;
import model.Seat;
import model.Show;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class BookingDAO {
    public boolean addBooking(Booking booking)
    {
        String bookingsql = """
                INSERT INTO booking
                (user_id,show_id)
                VALUES (?,?)
                """;
        String seatsql = """
                INSERT INTO booking_seat
                (booking_id,show_id,seat_id)
                VALUES (?,?,?)
                """;
        try(Connection connection = DBConnection.getConnection())
        {
            connection.setAutoCommit(false);
            try(PreparedStatement statement = connection.prepareStatement(bookingsql,
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, booking.getUser().getUserId());
                statement.setInt(2, booking.getShow().getShowId());

                statement.executeUpdate();
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        int bookingId = resultSet.getInt("booking_id");
                        booking.setBookingId(bookingId);
                    } else {
                        connection.rollback();
                        return false;
                    }
                }
                try (PreparedStatement seatStatement = connection.prepareStatement(seatsql)) {
                    for (Seat seat : booking.getSeats()) {
                        seatStatement.setInt(1, booking.getBookingId());
                        seatStatement.setInt(2, booking.getShow().getShowId());
                        seatStatement.setInt(3, seat.getSeatId());

                        seatStatement.executeUpdate();
                    }
                }
                connection.commit();
                return true;
            }
            catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
                return false;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<Booking> getBookingsByUser(User user)
    {
        Map<Integer,Booking> bookingMap = new HashMap<>();
        String sql = """
                SELECT
                b.booking_id,
                
                u.user_id,
                u.user_name,
                u.user_phone,
                
                s.show_id,
                s.movie_id,
                s.screen_id,
                s.show_date,
                s.show_time,
                
                seat.seat_id,
                seat.seat_row,
                seat.seat_no,
                seat.seat_type
                
                FROM booking b
                JOIN "user" u
                ON b.user_id = u.user_id
                
                JOIN show s ON b.show_id = s.show_id
                
                JOIN booking_seat bs ON bs.booking_id = b.booking_id
                
                JOIN seat ON bs.seat_id = seat.seat_id
                
                WHERE b.user_id = ?
                """;
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1,user.getUserId());

            try(ResultSet resultSet = statement.executeQuery())
            {
                while(resultSet.next())
                {
                    int bookingId = resultSet.getInt("booking_id");

                    int userId = resultSet.getInt("user_id");
                    String userName = resultSet.getString("user_name");
                    String userPhone = resultSet.getString("user_phone");

                    int showId = resultSet.getInt("show_id");
                    int movieId = resultSet.getInt("movie_id");
                    int screenId = resultSet.getInt("screen_id");
                    LocalDate date = resultSet.getDate("show_date").toLocalDate();
                    LocalTime time = resultSet.getTime("show_time").toLocalTime();

                    int seatId = resultSet.getInt("seat_id");
                    String row = resultSet.getString("seat_row");
                    int seatNo = resultSet.getInt("seat_no");
                    String type = resultSet.getString("seat_type");

                    Seat seat = new Seat(row,seatNo,type);
                    seat.setSeatId(seatId);

                    Booking booking = bookingMap.get(bookingId);
                    if(booking == null)
                    {
                        User user1 = new User(userName,userPhone);
                        user1.setUserId(userId);

                        Show show = new Show(movieId,screenId,date,time);
                        show.setShowId(showId);

                        ArrayList<Seat> seats = new ArrayList<>();
                        seats.add(seat);

                        Booking booking1 = new Booking(user1,show,seats);
                        booking1.setBookingId(bookingId);

                        bookingMap.put(bookingId,booking1);
                    }
                    else {
                        booking.getSeats().add(seat);
                    }
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return new ArrayList<>(bookingMap.values());
    }
    public Set<Integer> getBookedSeatIdsByShow(int showId)
    {
        Set<Integer> bookedSeatIds = new HashSet<>();
        String sql = """
                SELECT seat_id
                FROM booking_seat
                WHERE show_id = ?
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                )
        {
            statement.setInt(1,showId);

            try(ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    int seatId = resultSet.getInt("seat_id");

                    bookedSeatIds.add(seatId);
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return bookedSeatIds;
    }
    public ArrayList<Booking> getBookingByShow(int showId)
    {
        Map<Integer,Booking> mapBooking = new HashMap<>();
        String sql = """
                SELECT b.booking_id,
                u.user_id,
                u.user_name,
                u.user_phone,
                s.show_id,
                s.movie_id,
                s.screen_id,
                s.show_date,
                s.show_time,
                seat.seat_id,
                seat.seat_row,
                seat.seat_no,
                seat.seat_type
                FROM booking b
                JOIN "user" u
                ON b.user_id = u.user_id
                JOIN show s ON b.show_id = s.show_id
                JOIN booking_seat bs ON b.booking_id = bs.booking_id
                JOIN seat seat ON bs.seat_id = seat.seat_id
                WHERE s.show_id = ?
                """;
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
            )
        {
            statement.setInt(1,showId);
            try(ResultSet resultSet = statement.executeQuery())
            {
                while(resultSet.next())
                {
                    int bookingId = resultSet.getInt("booking_id");
                    int userId = resultSet.getInt("user_id");
                    String userName = resultSet.getString("user_name");
                    String userPhone = resultSet.getString("user_phone");
                    int movieId = resultSet.getInt("movie_id");
                    int screenId = resultSet.getInt("screen_id");
                    LocalDate date = resultSet.getDate("show_date").toLocalDate();
                    LocalTime time = resultSet.getTime("show_time").toLocalTime();
                    int seatId = resultSet.getInt("seat_id");
                    String seatRow = resultSet.getString("seat_row");
                    int  seatNo = resultSet.getInt("seat_no");
                    String seatType = resultSet.getString("seat_type");

                    Seat seat = new Seat(seatRow,seatNo,seatType);
                    seat.setSeatId(seatId);
                    Booking booking = mapBooking.get(bookingId);
                    if(booking == null)
                    {
                        User user = new User(userName,userPhone);
                        user.setUserId(userId);

                        Show show = new Show(movieId,screenId,date,time);
                        show.setShowId(showId);

                        ArrayList<Seat> seats = new ArrayList<>();
                        seats.add(seat);

                        Booking booking1 = new Booking(user,show,seats);
                        booking1.setBookingId(bookingId);

                        mapBooking.put(bookingId,booking1);
                    }
                    else {
                        booking.getSeats().add(seat);
                    }
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return new ArrayList<>(mapBooking.values());
    }
}
