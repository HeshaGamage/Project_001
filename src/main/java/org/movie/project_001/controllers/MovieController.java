package org.movie.project_001.controllers;

import org.movie.project_001.models.Movie;
import org.movie.project_001.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/movie")

public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie> getAllMovies() throws IOException {
        return movieService.getAllMovies();
    }
    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable UUID id) throws IOException {
        return movieService.getMovieById(id);
    }
    @GetMapping("/search")
    public List<Movie> searchMoviesByTitle(@RequestParam String title) throws IOException {
        return movieService.searchMoviesByTitle(title);
    }


    @PostMapping
    public void saveMovie(@RequestBody Movie movie) throws IOException {
        movieService.saveMovie(movie);
    }
    @PutMapping("/{id}")
    public void updateMovie(@PathVariable UUID id,@RequestBody Movie updatedmovie) throws IOException {
        movieService.updateMovie(id, updatedmovie);
    }
    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable UUID id) throws IOException {
        movieService.deleteMovie(id);
    }

}
