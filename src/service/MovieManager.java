package service;

import model.Movie;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MovieManager {
    private List<Movie> movies;
    public MovieManager()
    {
        movies = new ArrayList<>();
    }

    public boolean addMovie(Movie movie)
    {
        return movies.add(movie);
    }

    public boolean deleteMovie(String movieTitle)
    {
        Movie foundMovie=null;
        for(Movie movie:movies)
        {
            if(movie.getTitle().equalsIgnoreCase(movieTitle))
            {
                foundMovie=movie;
                break;
            }
        }
        if (foundMovie==null)
        {
            return false;
        }
        return movies.remove(foundMovie);
    }
    public boolean updateMovieTitle(String newMovieTitle,String oldMovieTitle)
    {
        Movie foundMovie=null;
        if(oldMovieTitle.equalsIgnoreCase(newMovieTitle))
        {
            return false;
        }
        for(Movie movie : movies)
        {
            if(movie.getTitle().equalsIgnoreCase(oldMovieTitle))
            {
                foundMovie = movie;
                break;
            }
        }
        if(foundMovie==null)
        {
            return false;
        }
        foundMovie.setTitle(newMovieTitle);
        return true;
    }
    public boolean updateMovieProductionHouse(String movieTitle,String newProductionHouse,String oldProductionHouse)
    {
        Movie foundMovie=null;
        if(oldProductionHouse.equalsIgnoreCase(newProductionHouse))
        {
            return false;
        }
        for(Movie movie : movies)
        {
            if(movie.getTitle().equalsIgnoreCase(movieTitle))
            {
                foundMovie = movie;
                break;
            }
        }
        if(foundMovie==null)
        {
            return false;
        }
        foundMovie.setProductionHouse(newProductionHouse);
        return true;
    }
    public boolean updateMovieProducer(String movieTitle,String newProducer,String oldProducer)
    {
        Movie foundMovie=null;
        if(oldProducer.equalsIgnoreCase(newProducer))
        {
            return false;
        }
        for(Movie movie : movies)
        {
            if(movie.getTitle().equalsIgnoreCase(movieTitle))
            {
                foundMovie = movie;
                break;
            }
        }
        if(foundMovie==null)
        {
            return false;
        }
        foundMovie.setProducer(newProducer);
        return true;
    }
    public boolean updateMovieDirector(String movieTitle,String newDirector,String oldDirector)
    {
        Movie foundMovie=null;
        if(oldDirector.equalsIgnoreCase(newDirector))
        {
            return false;
        }
        for(Movie movie : movies)
        {
            if(movie.getTitle().equalsIgnoreCase(movieTitle))
            {
                foundMovie = movie;
                break;
            }
        }
        if(foundMovie==null)
        {
            return false;
        }
        foundMovie.setDirector(newDirector);
        return true;
    }
    public boolean updateMovieLanguage(String movieTitle,String oldLanguage,String newLanguage)
    {
        Movie foundMovie=null;
        if(oldLanguage.equalsIgnoreCase(newLanguage))
        {
            return false;
        }
        for(Movie movie : movies)
        {
            if(movie.getTitle().equalsIgnoreCase(movieTitle))
            {
                foundMovie = movie;
                break;
            }
        }
        if(foundMovie==null)
        {
            return false;
        }
        foundMovie.setLanguage(newLanguage);
        return true;
    }
    public boolean updateMovieDuration(String movieTitle, Duration oldDuration, Duration newDuration)
    {
        Movie foundMovie=null;
        if(oldDuration.equals(newDuration))
        {
            return false;
        }
        for(Movie movie : movies)
        {
            if(movie.getTitle().equalsIgnoreCase(movieTitle))
            {
                foundMovie = movie;
                break;
            }
        }
        if(foundMovie==null)
        {
            return false;
        }
        foundMovie.setDuration(newDuration);
        return true;
    }
    public boolean updateMovieGenre(String movieTitle,String oldGenre,String newGenre)
    {
        Movie foundMovie=null;
        if(oldGenre.equalsIgnoreCase(newGenre))
        {
            return false;
        }
        for(Movie movie : movies)
        {
            if(movie.getTitle().equalsIgnoreCase(movieTitle))
            {
                foundMovie = movie;
                break;
            }
        }
        if(foundMovie==null)
        {
            return false;
        }
        foundMovie.setGenre(newGenre);
        return true;
    }
    public Movie findMovie(String movieTitle)
    {
        Movie foundMovie=null;
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
}
