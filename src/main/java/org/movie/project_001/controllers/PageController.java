package org.movie.project_001.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.movie.project_001.models.Movie;
import org.movie.project_001.models.Rental;
import org.movie.project_001.models.User;
import org.movie.project_001.models.WishList;
import org.movie.project_001.service.MovieService;
import org.movie.project_001.service.RentalService;
import org.movie.project_001.service.UserService;
import org.movie.project_001.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class PageController {
    @Autowired
    MovieService movieService;
    @Autowired
    RentalService rentalService;
    @Autowired
    WishListService wishlistService;
    @Autowired
    UserService userService;

    @GetMapping("/home")
    public String homePage(HttpServletRequest request, Model model, @SessionAttribute("userId") UUID userId) throws IOException {
        List<Movie> movies = movieService.getAllMovies(); // You need this service
        model.addAttribute("movies", movies);
        List<Rental> rentals = rentalService.getRentalByUserId(userId);
        String[] rentedMovieIds = rentals.stream()
                .map(rental -> rental.getMovieId().toString()) // Extract movieId and convert to String
                .toArray(String[]::new);
        model.addAttribute("rentedMovieIds", rentedMovieIds);

        request.setAttribute("originalPath", "/home");
        request.setAttribute("contentPage", "/WEB-INF/jsp/pages/home.jsp");
        request.setAttribute("pageCss", "/css/home.css");
        return "main";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String redirectSignup() {
        return "redirect:/signup";
    }

    @GetMapping("/wishlist")
    public String wishlistPage(HttpServletRequest request, Model model, HttpSession session) throws IOException {
        // Retrieve userId from the session
        UUID userId = (UUID) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login"; // Redirect to login if the user is not logged in
        }

        // Fetch the User object using the userId
        User user = userService.getUserById(userId);

        WishList wishlist = wishlistService.getWishlistByUserId(userId);
        List<Movie> movies = new ArrayList<>();

        // Fetch movie details for each ID in the wishlist
        if (wishlist != null) {
            for (UUID movieId : wishlist.getMovieIds()) {
                Movie movie = movieService.getMovieById(movieId);
                if (movie != null) {
                    movies.add(movie);
                }
            }
        }
        model.addAttribute("movies", movies);

        request.setAttribute("originalPath", "/wishlist");
        request.setAttribute("contentPage", "/WEB-INF/jsp/pages/wishlist.jsp");
        request.setAttribute("pageCss", "/css/wishlist.css");
        return "main";
    }
}

