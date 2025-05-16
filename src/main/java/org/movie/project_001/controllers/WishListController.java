package org.movie.project_001.controllers;

import org.movie.project_001.models.WishList;
import org.movie.project_001.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/wishlist")
public class WishListController {
    @Autowired
    private WishListService wishlistService;

    @GetMapping("/all")

    public List<WishList> getAllWishlists() throws IOException {
        return wishlistService.getAllWishlists();
    }

    @GetMapping("/{userId}")
    public WishList getWishlistByUserId(@PathVariable UUID userId) throws IOException {
        return wishlistService.getWishlistByUserId(userId);
    }

    @PostMapping("/add")
    public void addMovieToWishlist(@RequestParam UUID userId, @RequestParam UUID movieId) throws IOException {
        wishlistService.addMovieToWishlist(userId, movieId);
    }

    @PostMapping("/remove")
    public String removeMovieFromWishlist(@RequestParam UUID userId, @RequestParam UUID movieId) throws IOException {
        wishlistService.removeMovieFromWishlist(userId, movieId);
        // Redirect back to the wishlist page after removing the movie
        return "redirect:/wishlist";
    }
}
