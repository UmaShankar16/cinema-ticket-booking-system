package DAO;

import DB.DBConnection;
import model.Admin;
import model.CinemaHall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
    public boolean addAdmin(CinemaHall hall, Admin admin)
    {
        String sql = """
                INSERT INTO admin
                (admin_username,admin_password,hall_id)
                VALUES (?,?,?)
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql,
                        PreparedStatement.RETURN_GENERATED_KEYS);
                )
        {
            statement.setString(1, admin.getAdminUserName());
            statement.setString(2, admin.getPassword());
            statement.setInt(3,hall.getHallId());

            statement.executeUpdate();

            try(ResultSet resultSet = statement.getGeneratedKeys())
            {
                if(resultSet.next())
                {
                    int adminId = resultSet.getInt("admin_id");
                    admin.setAdminId(adminId);
                }
            }
            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public Admin findAdminByUserName(String userName)
    {
        Admin foundAdmin=null;
        String sql = """
                SELECT admin_id,admin_username,admin_password,hall_id
                FROM admin
                WHERE admin_username = ?
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                )
        {
            statement.setString(1,userName);

            try(ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    int adminId = resultSet.getInt("admin_id");
                    String userName1 = resultSet.getString("admin_username");
                    String password = resultSet.getString("admin_password");
                    int hallId = resultSet.getInt("hall_id");

                    foundAdmin = new Admin(userName1,password);
                    foundAdmin.setAdminId(adminId);
                    foundAdmin.setHallId(hallId);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return foundAdmin;
    }
    public boolean updateAdminUserName(Admin admin,String newUserName)
    {
        String sql= """
                UPDATE admin
                SET admin_username = ?
                WHERE admin_id=?
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                )
        {
            statement.setString(1,newUserName);
            statement.setInt(2,admin.getAdminId());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected>0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateAdminPassword(Admin admin,String newPassword)
    {
        String sql= """
                UPDATE admin
                SET admin_password = ?
                WHERE admin_id=?
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        )
        {
            statement.setString(1,newPassword);
            statement.setInt(2,admin.getAdminId());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected>0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
