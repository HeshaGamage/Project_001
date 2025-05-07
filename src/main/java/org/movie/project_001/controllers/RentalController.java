package org.movie.project_001.controllers;

import org.movie.project_001.models.Rental;
import org.movie.project_001.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.io.IOException;

@RestController
@RequestMapping("/rentals")
public class RentalController {
    @Autowired
    private RentalService rentalService;

    @GetMapping
    public List<Rental> getAllRentals() throws IOException {
        return rentalService.getAllRental();
    }

    @GetMapping("/{id}")
    public Rental getRentalByUserId(@PathVariable UUID id) throws IOException {
        return rentalService.getRentalByUserId(id);
    }

    @PostMapping
    public void rentMovie(@RequestParam UUID movieId, @RequestParam UUID userId, @RequestParam String rentalDate) throws IOException {
        rentalService.rentMovie(movieId, userId, rentalDate);
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
