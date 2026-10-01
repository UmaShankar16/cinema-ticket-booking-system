package DAO;

import DB.DBConnection;
import model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;

public class MovieDAO {
    public boolean addMovie(Movie movie)
    {
        String sql = """
                INSERT INTO movie
                (movie_title,movie_productionhouse,movie_producer,movie_director,
                movie_language,movie_duration_minutes,movie_genre)
                VALUES (?,?,?,?,?,?,?)
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql,
                        PreparedStatement.RETURN_GENERATED_KEYS);
                )
        {
            statement.setString(1,movie.getTitle());
            statement.setString(2,movie.getProductionHouse());
            statement.setString(3,movie.getProducer());
            statement.setString(4,movie.getDirector());
            statement.setString(5,movie.getLanguage());
            statement.setInt(6,(int) movie.getDuration().toMinutes());
            statement.setString(7, movie.getGenre());

            statement.executeUpdate();
            try(ResultSet resultSet = statement.getGeneratedKeys())
            {
                if(resultSet.next())
                {
                    int movieId = resultSet.getInt("movie_id");
                    movie.setMovieId(movieId);
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
    public boolean deleteMovieById(int movieId)
    {
        String sql = """
                DELETE FROM movie
                WHERE movie_id = ?
                """;
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        )
        {
            statement.setInt(1,movieId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected>0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateMovieTitle(int movieId,String newTitle)
    {
        String sql = """
                UPDATE movie
                SET movie_title = ?
                WHERE movie_id = ? 
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                )
        {
            statement.setString(1,newTitle);
            statement.setInt(2,movieId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected>0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateMovieProductionHouse(int movieId,String newProd)
    {
        String sql = """
                UPDATE movie
                SET movie_productionhouse = ?
                WHERE movie_id = ? 
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        )
        {
            statement.setString(1,newProd);
            statement.setInt(2,movieId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected>0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateMovieProducer(int movieId,String newProducer)
    {
        String sql = """
                UPDATE movie
                SET movie_producer = ?
                WHERE movie_id = ? 
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        )
        {
            statement.setString(1,newProducer);
            statement.setInt(2,movieId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected>0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateMovieDirector(int movieId,String newDirector)
    {
        String sql = """
                UPDATE movie
                SET movie_director = ?
                WHERE movie_id = ? 
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        )
        {
            statement.setString(1,newDirector);
            statement.setInt(2,movieId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected>0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateMovieLanguage(int movieId,String newLanguage)
    {
        String sql = """
                UPDATE movie
                SET movie_language = ?
                WHERE movie_id = ? 
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        )
        {
            statement.setString(1,newLanguage);
            statement.setInt(2,movieId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected>0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateMovieDuration(int movieId, Duration newDuration)
    {
        String sql = """
                UPDATE movie
                SET movie_duration_minutes = ?
                WHERE movie_id = ? 
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        )
        {
            int durationMinute = Math.toIntExact(newDuration.toMinutes());
            statement.setInt(1,durationMinute);
            statement.setInt(2,movieId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected>0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateMovieGenre(int movieId,String newGenre)
    {
        String sql = """
                UPDATE movie
                SET movie_genre = ?
                WHERE movie_id = ? 
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        )
        {
            statement.setString(1,newGenre);
            statement.setInt(2,movieId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected>0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<Movie> searchMovieByTitle(String movieTitle)
    {
        ArrayList<Movie> movies = new ArrayList<>();
        String sql = """
                SELECT * FROM movie
                WHERE LOWER(movie_title) = LOWER(?)
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                )
        {
            statement.setString(1,movieTitle);

            try(ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    int movieId = resultSet.getInt("movie_id");
                    String movieTitle1 = resultSet.getString("movie_title");
                    String productionHouse = resultSet.getString("movie_productionhouse");
                    String producer = resultSet.getString("movie_producer");
                    String director = resultSet.getString("movie_director");
                    String language = resultSet.getString("movie_language");
                    int durationMinutes = resultSet.getInt("movie_duration_minutes");
                    Duration duration = Duration.ofMinutes(durationMinutes);
                    String genre = resultSet.getString("movie_genre");

                    Movie movie = new Movie(movieTitle1,productionHouse,producer,director,language,duration,genre);
                    movie.setMovieId(movieId);
                    movies.add(movie);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return movies;
    }
    public ArrayList<Movie> getAllMovies()
    {
        ArrayList<Movie> movies = new ArrayList<>();
        String sql = """
                SELECT * FROM movie
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
                    int movieId = resultSet.getInt("movie_id");
                    String movieTitle1 = resultSet.getString("movie_title");
                    String productionHouse = resultSet.getString("movie_productionhouse");
                    String producer = resultSet.getString("movie_producer");
                    String director = resultSet.getString("movie_director");
                    String language = resultSet.getString("movie_language");
                    int durationMinutes = resultSet.getInt("movie_duration_minutes");
                    Duration duration = Duration.ofMinutes(durationMinutes);
                    String genre = resultSet.getString("movie_genre");

                    Movie movie = new Movie(movieTitle1,productionHouse,producer,director,language,duration,genre);
                    movie.setMovieId(movieId);
                    movies.add(movie);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return movies;
    }
}
