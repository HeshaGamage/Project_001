package org.movie.project_001.controllers;


import org.movie.project_001.models.Movie;
import org.movie.project_001.models.Rental;
import org.movie.project_001.models.User;
import org.movie.project_001.service.MovieService;
import org.movie.project_001.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class PageController {
    @Autowired
    MovieService movieService;
    @Autowired
    RentalService rentalService;

    @GetMapping("/")
    public String homePage(Model model, @SessionAttribute("userId") UUID userId) throws IOException {
        List<Movie> movies = movieService.getAllMovies(); // You need this service
        model.addAttribute("movies", movies);
        List<Rental> rentals= rentalService.getRentalByUserId(userId);
        String[] rentedMovieIds = rentals.stream()
                .map(rental -> rental.getMovieId().toString()) // Extract movieId and convert to String
                .toArray(String[]::new);
        model.addAttribute("rentedMovieIds", rentedMovieIds);
        return "home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String redirectSignup() {
        return "redirect:/signup";
    }




}

