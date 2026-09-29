package DAO;

import DB.DBConnection;
import model.CinemaHall;
import model.Screen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScreenDAO {
    public boolean addScreen(CinemaHall hall, Screen screen)
    {
        String sql = """
                INSERT INTO screen
                (screen_no,hall_id)
                VALUES (?,?)
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql,
                        PreparedStatement.RETURN_GENERATED_KEYS);
                )
        {
            statement.setInt(1,screen.getScreenNo());
            statement.setInt(2,hall.getHallId());

            statement.executeUpdate();

            try(ResultSet resultSet = statement.getGeneratedKeys())
            {
                if (resultSet.next())
                {
                    int screenId = resultSet.getInt("screen_id");
                    screen.setScreenId(screenId);
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteScreen(CinemaHall hall,int screenNo)
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
            statement.setInt(2,screenNo);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected>0;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateScreen(CinemaHall hall,int newScreenNo,int oldScreenNo)
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
            statement.setInt(3,oldScreenNo);

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
