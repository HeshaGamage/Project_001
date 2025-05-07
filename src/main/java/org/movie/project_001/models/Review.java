package org.movie.project_001.models;

import java.util.UUID;

public class Review {
    private final UUID reviewId = UUID.randomUUID();
    private UUID userId;
    private UUID movieId;
    private double rating;
    private String comment;
    private String date;

    public Review(UUID reviewId, UUID userId, UUID movieId, double rating, String comment, String date) {
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }

    public Review() {
    }

    public UUID getReviewId() {
        return reviewId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getMovieId() {
        return movieId;
    }

    public void setMovieId(UUID movieId) {
        this.movieId = movieId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}


