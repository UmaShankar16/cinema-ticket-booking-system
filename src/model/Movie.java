package model;

import java.time.Duration;

public class Movie {
    private String title;
    private String productionHouse;
    private String producer;
    private String director;
    private String language;
    private Duration duration;
    private String genre;

    public Movie(String title,String productionHouse,String producer,
                 String director,String language,Duration duration,String genre)
    {
        this.title=title;
        this.productionHouse=productionHouse;
        this.producer=producer;
        this.director=director;
        this.language=language;
        this.duration=duration;
        this.genre=genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setProductionHouse(String productionHouse) {
        this.productionHouse = productionHouse;
    }

    public String getProductionHouse() {
        return productionHouse;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getProducer() {
        return producer;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDirector() {
        return director;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Duration  getDuration() {
        return duration;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }
}
