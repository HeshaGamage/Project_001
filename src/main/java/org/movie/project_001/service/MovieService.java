package org.movie.project_001.service;

import org.movie.project_001.models.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.util.UUID;

@Service
public class MovieService {
    private final ObjectMapper mapper = new ObjectMapper();
    private final String movie_path = "src/main/resources/data/movies.json";

    public List<Movie> getAllMovies() throws IOException {
        File movie_file = new File(movie_path);
        return Arrays.asList(mapper.readValue(movie_file,Movie[].class));
    }
    public Movie getMovieById(UUID movie_id) throws IOException {
            return getAllMovies().stream()
                    .filter(movie -> movie.getId().equals(movie_id))
                    .findFirst()
                    .orElse(null);

    }
    public void saveMovie(Movie movie) throws IOException {
        List<Movie> movies = new ArrayList<>(getAllMovies());
        movies.add(movie);
        mapper.writeValue(new File(movie_path), movies);
    }
    public void updateMovie(UUID id, Movie updatedMovie) throws IOException {
        List<Movie> movies = new ArrayList<>(getAllMovies());
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getId().equals(id)) {
                movies.set(i, updatedMovie); // Replace the old movie with the updated one
                break;
            }
        }
        mapper.writeValue(new File(movie_path), movies);
    }
    public void deleteMovie(UUID id) throws IOException {
        List<Movie> movies = new ArrayList<>(getAllMovies());
        movies.removeIf(movie -> movie.getId().equals(id));
        mapper.writeValue(new File(movie_path), movies);
    }
    public List<Movie> searchMoviesByTitle(String title) throws IOException {
        List<Movie> allMovies = getAllMovies();
        List<Movie> matchingMovies = new ArrayList<>();

        for (Movie movie : allMovies) {
            if (movie.getTitle() != null && movie.getTitle().toLowerCase().contains(title.toLowerCase())) {
                matchingMovies.add(movie);
            }
        }
        return matchingMovies;
    }




}
