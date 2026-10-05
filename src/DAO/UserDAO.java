package DAO;

import DB.DBConnection;
import model.Admin;
import model.User;

import java.sql.*;

public class UserDAO {
    public boolean addUser(User user)
    {
        String sql = """
                INSERT INTO "user"
                (user_name,user_phone)
                VALUES (?,?)
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql,
                        PreparedStatement.RETURN_GENERATED_KEYS);
                )
        {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPhone());

            statement.executeUpdate();
            try(ResultSet resultSet = statement.getGeneratedKeys())
            {
                if (resultSet.next())
                {
                    int userId = resultSet.getInt("user_id");
                    user.setUserId(userId);
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
    public User findUserByPhone(String phoneNo)
    {
        User foundUser=null;
        String sql = """
                SELECT user_id,user_name,user_phone
                FROM "user"
                WHERE user_phone = ?
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        )
        {
            statement.setString(1,phoneNo);

            try(ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    int userId = resultSet.getInt("user_id");
                    String userName = resultSet.getString("user_name");
                    String phone = resultSet.getString("user_phone");

                    foundUser = new User(userName,phone);
                    foundUser.setUserId(userId);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return foundUser;
    }
    public boolean updateUserName(User user,String newName)
    {
        String sql= """
                UPDATE "user"
                SET user_name = ?
                WHERE user_id=?
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        )
        {
            statement.setString(1,newName);
            statement.setInt(2,user.getUserId());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected>0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateUserPhoneNo(User user, String newPhone)
    {
        String sql= """
                UPDATE "user"
                SET user_phone = ?
                WHERE user_id=?
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        )
        {
            statement.setString(1,newPhone);
            statement.setInt(2, user.getUserId());

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
