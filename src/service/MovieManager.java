package service;

import DAO.MovieDAO;
import model.Movie;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MovieManager {
    private MovieDAO movieDAO;
    public MovieManager()
    {
        movieDAO = new MovieDAO();
    }

    public boolean addMovie(Movie movie)
    {
        return movieDAO.addMovie(movie);
    }
    public boolean deleteMovieById(int movieId)
    {
        return movieDAO.deleteMovieById(movieId);
    }
    public boolean updateMovieTitle(int movieId,String newMovieTitle,String oldMovieTitle)
    {
        if(oldMovieTitle.equalsIgnoreCase(newMovieTitle))
        {
            return false;
        }
        return movieDAO.updateMovieTitle(movieId,newMovieTitle);
    }
    public boolean updateMovieProductionHouse(int movieId,String newProductionHouse,String oldProductionHouse)
    {
        if(oldProductionHouse.equalsIgnoreCase(newProductionHouse))
        {
            return false;
        }
        return movieDAO.updateMovieProductionHouse(movieId,newProductionHouse);
    }
    public boolean updateMovieProducer(int movieId,String newProducer,String oldProducer)
    {
        if(oldProducer.equalsIgnoreCase(newProducer))
        {
            return false;
        }
        return movieDAO.updateMovieProducer(movieId,newProducer);
    }
    public boolean updateMovieDirector(int movieId,String newDirector,String oldDirector)
    {
        if(oldDirector.equalsIgnoreCase(newDirector))
        {
            return false;
        }
        return movieDAO.updateMovieDirector(movieId,newDirector);
    }
    public boolean updateMovieLanguage(int movieId,String newLanguage,String oldLanguage)
    {
        if(oldLanguage.equalsIgnoreCase(newLanguage))
        {
            return false;
        }
        return movieDAO.updateMovieLanguage(movieId,newLanguage);
    }
    public boolean updateMovieDuration(int movieId, Duration newDuration, Duration oldDuration)
    {
        if(oldDuration.equals(newDuration))
        {
            return false;
        }
        return movieDAO.updateMovieDuration(movieId,newDuration);
    }
    public boolean updateMovieGenre(int movieId,String newGenre,String oldGenre)
    {
        if(oldGenre.equalsIgnoreCase(newGenre))
        {
            return false;
        }
        return movieDAO.updateMovieGenre(movieId,newGenre);
    }
    public Movie findMovie(String movieTitle)
    {
        Movie foundMovie = null;
        ArrayList<Movie> movies = new ArrayList<>();
        for(Movie movie:movies)
        {
            if(movie.getTitle().equalsIgnoreCase(movieTitle))
            {
                foundMovie = movie;
                break;
            }
        }
        return foundMovie;
    }
    public ArrayList<Movie> searchMovieByTitle(String movieTitle)
    {
        return movieDAO.searchMovieByTitle(movieTitle);
    }
    public ArrayList<Movie> getAllMovies()
    {
        return movieDAO.getAllMovies();
    }
}
