package org.movie.project_001.controllers;

import org.springframework.stereotype.Controller;
import org.movie.project_001.models.Rental;
import org.movie.project_001.service.MovieService;
import org.movie.project_001.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.io.IOException;

@Controller
@RequestMapping("/rentals")
public class RentalController {
    @Autowired
    private RentalService rentalService;
    private MovieService movieService;

    @GetMapping
    public List<Rental> getAllRentals() throws IOException {
        return rentalService.getAllRental();
    }

    @GetMapping("/{id}")
    public List<Rental> getRentalByUserId(@PathVariable UUID id) throws IOException {
        return rentalService.getRentalByUserId(id);
    }

    @PostMapping
    public String rentMovie(@RequestParam UUID movieId,
                            @RequestParam UUID userId) throws IOException {

        rentalService.rentMovie(movieId, userId);

        return "redirect:/home";
    }

    @PutMapping("/return/{id}")
    public void returnMovie(@PathVariable UUID id) throws IOException {
        rentalService.returnMovie(id);
    }

    @PutMapping("/{id}")
    public void updateRental(@PathVariable UUID id, @RequestBody Rental updatedRental) throws IOException {
        rentalService.updateRental(id, updatedRental);
    }

    @DeleteMapping("/{id}")
    public void deleteRental(@PathVariable UUID id) throws IOException {
        rentalService.deleteRental(id);
    }
}
