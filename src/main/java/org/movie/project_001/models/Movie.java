package org.movie.project_001.models;

import java.util.List;
import java.util.UUID;

public class Movie {
    private final UUID id = UUID.randomUUID();
    private String title;
    private String description;
    private Double rating;
    private boolean available;
    private String posterUrl;

    //constructor
    public Movie() {
    }

    public Movie(String description, Double rating, boolean available, String title, List<Review> reviews) {
        this.description = description;
        this.rating = rating;
        this.available = available;
        this.title = title;
    }

    public UUID getId() { //only getter for ID because it's immutable
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
}



