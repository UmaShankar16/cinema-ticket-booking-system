package DAO;

import DB.DBConnection;
import model.Movie;
import model.Screen;
import model.Show;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ShowDAO {
    public boolean addShow(Screen screen, Show show)
    {
        String sql = """
                INSERT INTO show
                (movie_id,show_date,show_time,screen_id)
                VALUES (?,?,?,?) 
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql,
                        PreparedStatement.RETURN_GENERATED_KEYS);
                )
        {
            statement.setInt(1,show.getMovieId());
            statement.setDate(2,java.sql.Date.valueOf(show.getDate()));
            statement.setTime(3,java.sql.Time.valueOf(show.getStartTime()));
            statement.setInt(4,screen.getScreenId());

            statement.executeUpdate();
            try(ResultSet resultSet = statement.getGeneratedKeys())
            {
                if(resultSet.next())
                {
                    int showId = resultSet.getInt("show_id");
                    show.setShowId(showId);
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
    public boolean deleteShow(Screen screen,Show show)
    {
        String sql = """
                DELETE FROM show
                WHERE screen_id = ?
                AND movie_id = ?
                AND show_id = ?
                AND show_date = ?
                AND show_time = ?
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                )
        {
            statement.setInt(1,screen.getScreenId());
            statement.setInt(2,show.getMovieId());
            statement.setInt(3,show.getShowId());
            statement.setDate(4,java.sql.Date.valueOf(show.getDate()));
            statement.setTime(5,java.sql.Time.valueOf(show.getStartTime()));

            statement.executeUpdate();
            return true;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateShowDate(Screen screen, Show show, LocalDate newDate)
    {
        String sql = """
                UPDATE show
                SET show_date = ?
                WHERE screen_id = ?
                AND show_id = ?
                AND movie_id = ?
                AND show_date = ?
                AND show_time = ?
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                )
        {
            statement.setDate(1,java.sql.Date.valueOf(newDate));
            statement.setInt(2,screen.getScreenId());
            statement.setInt(3,show.getShowId());
            statement.setInt(4,show.getMovieId());
            statement.setDate(5,java.sql.Date.valueOf(show.getDate()));
            statement.setTime(6,java.sql.Time.valueOf(show.getStartTime()));

            int rowsAffected = statement.executeUpdate();
            return rowsAffected>0;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateShowTime(Screen screen, Show show, LocalTime newTime)
    {
        String sql = """
                UPDATE show
                SET show_time = ?
                WHERE screen_id = ?
                AND show_id = ?
                AND movie_id = ?
                AND show_date = ?
                AND show_time = ?
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        )
        {
            statement.setTime(1,java.sql.Time.valueOf(newTime));
            statement.setInt(2,screen.getScreenId());
            statement.setInt(3,show.getShowId());
            statement.setInt(4,show.getMovieId());
            statement.setDate(5,java.sql.Date.valueOf(show.getDate()));
            statement.setTime(6,java.sql.Time.valueOf(show.getStartTime()));

            int rowsAffected = statement.executeUpdate();
            return rowsAffected>0;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<Show> getAllShowsByScreen(Screen screen)
    {
        ArrayList<Show> foundShow = new ArrayList<>();
        String sql = """
                SELECT s.show_id,s.movie_id,m.movie_title,s.show_date,s.show_time
                FROM show s JOIN movie m
                ON s.movie_id = m.movie_id
                WHERE s.screen_id = ?
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
                    int showId = resultSet.getInt("show_id");
                    int movieId = resultSet.getInt("movie_id");
                    String movieTitle = resultSet.getString("movie_title");
                    LocalDate date = resultSet.getDate("show_date").toLocalDate();
                    LocalTime time = resultSet.getTime("show_time").toLocalTime();

                    Show show = new Show(movieId, screen.getScreenId(), date,time);
                    show.setShowId(showId);
                    foundShow.add(show);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return foundShow;
    }
    public ArrayList<Show> getAllShowsByMovie(Movie movie)
    {
        ArrayList<Show> foundShow = new ArrayList<>();
        String sql = """
                SELECT
                    s.show_id,
                    s.movie_id,
                    s.show_date,
                    s.show_time,
                    s.screen_id
                FROM show s
                WHERE s.movie_id = ?
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        )
        {
            statement.setInt(1,movie.getMovieId());

            try(ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    int showId = resultSet.getInt("show_id");
                    int movieId = resultSet.getInt("movie_id");
                    int screenId = resultSet.getInt("screen_id");
                    LocalDate date = resultSet.getDate("show_date").toLocalDate();
                    LocalTime time = resultSet.getTime("show_time").toLocalTime();

                    Show show = new Show(movieId,screenId,date,time);
                    show.setShowId(showId);
                    foundShow.add(show);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return foundShow;
    }
    public ArrayList<Show> getAllShowsByDate(LocalDate date)
    {
        ArrayList<Show> foundShow = new ArrayList<>();
        String sql = """
                SELECT s.show_id,s.movie_id,m.movie_title,s.show_date,s.show_time,s.screen_id
                FROM show s JOIN movie m
                ON s.movie_id = m.movie_id
                WHERE s.show_date = ?
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        )
        {
            statement.setDate(1,java.sql.Date.valueOf(date));

            try(ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    int showId = resultSet.getInt("show_id");
                    int movieId = resultSet.getInt("movie_id");
                    String movieTitle = resultSet.getString("movie_title");
                    int screenId = resultSet.getInt("screen_id");
                    LocalDate date1 = resultSet.getDate("show_date").toLocalDate();
                    LocalTime time = resultSet.getTime("show_time").toLocalTime();

                    Show show1 = new Show(movieId,screenId,date1,time);
                    show1.setShowId(showId);
                    foundShow.add(show1);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return foundShow;
    }
    public ArrayList<Show> getAllShows()
    {
        ArrayList<Show> foundShow = new ArrayList<>();
        String sql = """
                SELECT s.show_id,s.movie_id,m.movie_title,s.show_date,s.show_time,s.screen_id
                FROM show s JOIN movie m
                ON s.movie_id = m.movie_id
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
                    int showId = resultSet.getInt("show_id");
                    int movieId = resultSet.getInt("movie_id");
                    String movieTitle = resultSet.getString("movie_title");
                    int screenId = resultSet.getInt("screen_id");
                    LocalDate date = resultSet.getDate("show_date").toLocalDate();
                    LocalTime time = resultSet.getTime("show_time").toLocalTime();

                    Show show1 = new Show(movieId,screenId,date,time);
                    show1.setShowId(showId);
                    foundShow.add(show1);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return foundShow;
    }
}
