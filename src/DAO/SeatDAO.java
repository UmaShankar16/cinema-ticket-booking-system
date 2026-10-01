package DAO;

import DB.DBConnection;
import model.Screen;
import model.Seat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SeatDAO {
    public boolean addSeat(Screen screen, Seat seat)
    {
        String sql = """
                INSERT INTO seat
                (screen_id,seat_row,seat_no,seat_type)
                VALUES (?,?,?,?)
                """;
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                )
        {
            statement.setInt(1,screen.getScreenId());
            statement.setString(2,seat.getRow());
            statement.setInt(3,seat.getSeatNo());
            statement.setString(4,seat.getType());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected>0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteSeat(Screen screen,int seatNo,String seatRow)
    {
        String sql = """
                DELETE FROM seat
                WHERE screen_id = ?
                AND seat_row = ?
                AND seat_no = ?
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                )
        {
            statement.setInt(1,screen.getScreenId());
            statement.setString(2,seatRow);
            statement.setInt(3,seatNo);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected>0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateSeatNo(Screen screen,int oldSeatNo,int newSeatNo, String row)
    {
        String sql = """
                UPDATE seat
                SET seat_no = ?
                WHERE screen_id = ?
                AND seat_no = ?
                AND seat_row = ?
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                )
        {
            statement.setInt(1,newSeatNo);
            statement.setInt(2,screen.getScreenId());
            statement.setInt(3,oldSeatNo);
            statement.setString(4,row);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected>0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateSeatRow(Screen screen,String oldSeatRow,String newSeatRow)
    {
        String sql = """
                UPDATE seat
                SET seat_row = ?
                WHERE screen_id = ?
                AND seat_row = ?
                """;
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1,newSeatRow);
            statement.setInt(2, screen.getScreenId());
            statement.setString(3,oldSeatRow);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateSeatType(Screen screen,int seatNo,String newType,String oldType, String row)
    {
        String sql = """
                UPDATE seat
                SET seat_type = ?
                WHERE screen_id = ?
                AND seat_no = ?
                AND seat_row = ?
                AND seat_type = ?
                """;
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, newType);
            statement.setInt(2, screen.getScreenId());
            statement.setInt(3, seatNo);
            statement.setString(4, row);
            statement.setString(5,oldType);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<Seat> getAllSeat(Screen screen)
    {
        ArrayList<Seat> foundSeat = new ArrayList<>();
        String sql = """
                SELECT seat_id,seat_row,seat_no,seat_type
                FROM seat
                WHERE screen_id = ?
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                )
        {
            statement.setInt(1,screen.getScreenId());

            try(ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    int seatId = resultSet.getInt("seat_id");
                    String row = resultSet.getString("seat_row");
                    int no = resultSet.getInt("seat_no");
                    String type = resultSet.getString("seat_type");

                    Seat seat = new Seat(row,no,type);
                    seat.setSeatId(seatId);
                    foundSeat.add(seat);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return foundSeat;
    }
}
