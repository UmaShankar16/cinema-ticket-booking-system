package DAO;

import DB.DBConnection;
import model.CinemaHall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CinemaHallDAO {
    public boolean addCinemaHall(CinemaHall hall)
    {
        String sql = """
                INSERT INTO cinemahall
                (cinemahall_name,cinemahall_location)
                VALUES(?,?)
                """;
        try(
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);)
        {
            statement.setString(1, hall.getCinemaHallName());
            statement.setString(2, hall.getLocation());

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteCinemaHall(CinemaHall hall)
    {
        String sql = """
                DELETE FROM cinemahall
                WHERE LOWER(cinemahall_name) = LOWER(?)
                AND LOWER(cinemahall_location) = LOWER(?)
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                )
        {
            statement.setString(1, hall.getCinemaHallName());
            statement.setString(2, hall.getLocation());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected>0;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateCinemaHallLocation(CinemaHall hall,String newLocation)
    {
        String sql = """
                UPDATE cinemahall
                SET cinemahall_location = ?
                WHERE LOWER(cinemahall_name) = LOWER(?)
                AND LOWER(cinemahall_location) = LOWER(?)
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                )
        {
            statement.setString(1,newLocation);
            statement.setString(2, hall.getCinemaHallName());
            statement.setString(3, hall.getLocation());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected>0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateCinemaHallName(CinemaHall hall,String newName)
    {
        String sql = """
                UPDATE cinemahall
                SET cinemahall_name = ?
                WHERE LOWER(cinemahall_name) = LOWER(?)
                AND LOWER(cinemahall_location) = LOWER(?)
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                )
        {
            statement.setString(1,newName);
            statement.setString(2, hall.getCinemaHallName());
            statement.setString(3, hall.getLocation());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected>0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<CinemaHall> searchCinemaHallByName(String hallName)
    {
        ArrayList<CinemaHall> foundHall = new ArrayList<>();
        String sql = """
                SELECT * FROM cinemahall
                WHERE LOWER(cinemahall_name) = LOWER(?)
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                )
        {
            statement.setString(1,hallName);

            try(ResultSet resultSet = statement.executeQuery())
            {
                while(resultSet.next())
                {
                    int hallId = resultSet.getInt("hall_id");
                    String name = resultSet.getString("cinemahall_name");
                    String location = resultSet.getString("cinemahall_location");

                    CinemaHall hall = new CinemaHall(name,location);
                    hall.setHallId(hallId);
                    foundHall.add(hall);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return foundHall;
    }
    public ArrayList<CinemaHall> searchCinemaHallByLocation(String hallLocation)
    {
        ArrayList<CinemaHall> foundHall = new ArrayList<>();
        String sql = """
                SELECT * FROM cinemahall
                WHERE LOWER(cinemahall_location) = LOWER(?)
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        )
        {
            statement.setString(1,hallLocation);

            try(ResultSet resultSet = statement.executeQuery())
            {
                while(resultSet.next())
                {
                    int hallId = resultSet.getInt("hall_id");
                    String name = resultSet.getString("cinemahall_name");
                    String location = resultSet.getString("cinemahall_location");

                    CinemaHall hall = new CinemaHall(name,location);
                    hall.setHallId(hallId);
                    foundHall.add(hall);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return foundHall;
    }
    public CinemaHall searchCinemaHallByNameAndLocation(CinemaHall hall)
    {
        CinemaHall hall1 = null;
        String sql = """
                SELECT * FROM cinemahall
                WHERE LOWER(cinemahall_name) = LOWER(?)
                AND LOWER(cinemahall_location) = LOWER(?)
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        )
        {
            statement.setString(1,hall.getCinemaHallName());
            statement.setString(2,hall.getLocation());

            try(ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    int hallId = resultSet.getInt("hall_id");
                    String name = resultSet.getString("cinemahall_name");
                    String location = resultSet.getString("cinemahall_location");

                    hall1 = new CinemaHall(name,location);
                    hall1.setHallId(hallId);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return hall1;
    }
    public ArrayList<CinemaHall> getAllCinemaHall()
    {
        ArrayList<CinemaHall> allHalls = new ArrayList<>();
        String sql = """
                SELECT * FROM cinemahall
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                )
        {
            try(ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    int hallId = resultSet.getInt("hall_id");
                    String name = resultSet.getString("cinemahall_name");
                    String  location = resultSet.getString("cinemahall_location");

                    CinemaHall hall = new CinemaHall(name,location);
                    hall.setHallId(hallId);
                    allHalls.add(hall);
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return allHalls;
    }
}
