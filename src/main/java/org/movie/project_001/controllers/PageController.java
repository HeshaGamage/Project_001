package org.movie.project_001.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.movie.project_001.models.*;
import org.movie.project_001.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.swing.text.html.Option;
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
    ReviewService reviewService;

    @GetMapping("/home")
    public String homePage(HttpServletRequest request, Model model, @SessionAttribute("userId") UUID userId) throws IOException {
        List<Movie> movies = movieService.getAllMovies(); // You need this service
        model.addAttribute("movies", movies);

        List<Rental> rentals = rentalService.getRentalByUserId(userId);
        String[] rentedMovieIds = rentals.stream().map(rental -> rental.getMovieId().toString()).toArray(String[]::new);
        model.addAttribute("rentedMovieIds", rentedMovieIds);

        WishList wishList = wishlistService.getWishlistByUserId(userId);
        String[] wishListMovieIds = wishList.getMovieIds().stream().map(UUID::toString).toArray(String[]::new);
        model.addAttribute("wishList", wishListMovieIds);

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
            return "redirect:/login"; // Redirect to log in if the user is not logged in
        }

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

    @GetMapping("/movie-details/{id}")
    public String loadMovieDetailsPage(HttpServletRequest request, Model model, HttpSession session, @PathVariable UUID id) throws IOException {
        // Retrieve userId from the session
        UUID userId = (UUID) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login"; // Redirect to log in if the user is not logged in
        }

        Movie movie = movieService.getMovieById(id);
        model.addAttribute("movie", movie);

        WishList userWishList = wishlistService.getWishlistByUserId(userId);
        boolean isMovieInUserWishList = userWishList != null && userWishList.getMovieIds().contains(id);
        model.addAttribute("isMovieInUserWishList", isMovieInUserWishList);

        List<Rental> userRentals = rentalService.getRentalByUserId(userId);
        boolean isMovieRented = userRentals != null && userRentals.stream().anyMatch(rental -> rental.getMovieId().equals(id));
        model.addAttribute("isMovieRented", isMovieRented);

        List<Review> reviews = reviewService.getReviewByMovieId(id);
        model.addAttribute("reviews", reviews);

        request.setAttribute("originalPath", "/movie-details");
        request.setAttribute("contentPage", "/WEB-INF/jsp/pages/movie-details.jsp");
        request.setAttribute("pageCss", "/css/movie-details.css");
        return "main";
    }
}

