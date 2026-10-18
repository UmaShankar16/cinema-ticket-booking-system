package DAO;

import DB.DBConnection;
import model.CinemaHall;
import model.Screen;
import model.Seat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScreenDAO {
    public boolean addScreen(CinemaHall hall, Screen screen)
    {

        String screenSql = """
            INSERT INTO screen (screen_no, hall_id)
            VALUES (?, ?)
            """;

        String seatSql = """
            INSERT INTO seat
            (screen_id, seat_row, seat_no, seat_type)
            VALUES (?, ?, ?, ?)
            """;

        Connection connection = null;

        try {
            connection = DBConnection.getConnection();
            connection.setAutoCommit(false);

            String checksql = """
                SELECT 1 FROM
                screen
                WHERE screen_no=?
                AND hall_id = ?
                """;

            try(PreparedStatement checkStatement = connection.prepareStatement(checksql))
            {
                checkStatement.setInt(1,screen.getScreenNo());
                checkStatement.setInt(2,hall.getHallId());

                try(ResultSet resultSet = checkStatement.executeQuery())
                {
                    if(resultSet.next())
                    {
                        connection.rollback();
                        return false;
                    }
                }
            }

            try (PreparedStatement screenStatement =
                         connection.prepareStatement(
                                 screenSql,
                                 java.sql.Statement.RETURN_GENERATED_KEYS)) {

                screenStatement.setInt(1, screen.getScreenNo());
                screenStatement.setInt(2, hall.getHallId());

                int rowsAffected = screenStatement.executeUpdate();

                if (rowsAffected == 0) {
                    connection.rollback();
                    return false;
                }
                try (ResultSet resultSet = screenStatement.getGeneratedKeys()) {

                    if (resultSet.next()) {
                        int screenId = resultSet.getInt(1);
                        screen.setScreenId(screenId);
                    } else {
                        connection.rollback();
                        return false;
                    }
                }
            }
            try (PreparedStatement seatStatement =
                         connection.prepareStatement(seatSql)) {

                for (Seat seat : screen.getSeats()) {

                    seatStatement.setInt(1, screen.getScreenId());
                    seatStatement.setString(2, seat.getRow());
                    seatStatement.setInt(3, seat.getSeatNo());
                    seatStatement.setString(4, seat.getType());

                    seatStatement.addBatch();
                }
                if (!screen.getSeats().isEmpty()) {
                    seatStatement.executeBatch();
                }
            }
            connection.commit();

            return true;
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public boolean deleteScreen(CinemaHall hall,Screen screen)
    {
        String sql = """
                DELETE FROM screen
                WHERE hall_id = ?
                AND screen_no = ?
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                )
        {
            statement.setInt(1,hall.getHallId());
            statement.setInt(2,screen.getScreenNo());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected>0;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateScreen(CinemaHall hall,Screen screen,int newScreenNo)
    {
        String sql = """
                UPDATE screen
                SET screen_no = ?
                WHERE hall_id = ?
                AND screen_no = ?
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                )
        {
            statement.setInt(1,newScreenNo);
            statement.setInt(2,hall.getHallId());
            statement.setInt(3,screen.getScreenNo());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected>0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<Screen> getAllScreenOfCinemaHall(CinemaHall hall)
    {
        ArrayList<Screen> allScreen = new ArrayList<>();
        String sql = """
                SELECT screen_id,screen_no FROM
                screen WHERE 
                hall_id = ?
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                )
        {
            statement.setInt(1,hall.getHallId());

            try(ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    int screenNo = resultSet.getInt("screen_no");
                    int screenId = resultSet.getInt("screen_id");

                    Screen screen = new Screen(screenNo);
                    screen.setScreenId(screenId);
                    allScreen.add(screen);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return allScreen;
    }
}
