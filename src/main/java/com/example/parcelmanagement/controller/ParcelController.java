package com.example.parcelmanagement.controller;

import com.example.parcelmanagement.model.Parcel;
import com.example.parcelmanagement.service.ParcelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Parcel management
 * Handles all HTTP requests related to parcels
 * Base URL: /api/parcels
 */
@RestController
@RequestMapping("/api/parcels")
@CrossOrigin(origins = "*") // Enable CORS for React frontend
public class ParcelController {

    private final ParcelService parcelService;

    // Constructor injection for service
    public ParcelController(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    /**
     * Create a new parcel
     * POST /api/parcels
     * @param parcel Parcel object from request body
     * @return Created parcel with HTTP 201 status
     */
    @PostMapping
    public ResponseEntity<Parcel> createParcel(@RequestBody Parcel parcel) {
        Parcel createdParcel = parcelService.createParcel(parcel);
        return new ResponseEntity<>(createdParcel, HttpStatus.CREATED);
    }

    /**
     * Get all parcels
     * GET /api/parcels
     * @return List of all parcels with HTTP 200 status
     */
    @GetMapping
    public ResponseEntity<List<Parcel>> getAllParcels() {
        List<Parcel> parcels = parcelService.getAllParcels();
        return new ResponseEntity<>(parcels, HttpStatus.OK);
    }

    /**
     * Get a parcel by ID
     * GET /api/parcels/{id}
     * @param id Parcel ID from path variable
     * @return Parcel object with HTTP 200 status
     */
    @GetMapping("/{id}")
    public ResponseEntity<Parcel> getParcelById(@PathVariable Long id) {
        Parcel parcel = parcelService.getParcelById(id);
        return new ResponseEntity<>(parcel, HttpStatus.OK);
    }

    /**
     * Update an existing parcel
     * PUT /api/parcels/{id}
     * @param id Parcel ID from path variable
     * @param parcel Updated parcel data from request body
     * @return Updated parcel with HTTP 200 status
     */
    @PutMapping("/{id}")
    public ResponseEntity<Parcel> updateParcel(@PathVariable Long id, @RequestBody Parcel parcel) {
        Parcel updatedParcel = parcelService.updateParcel(id, parcel);
        return new ResponseEntity<>(updatedParcel, HttpStatus.OK);
    }

    /**
     * Delete a parcel
     * DELETE /api/parcels/{id}
     * @param id Parcel ID from path variable
     * @return HTTP 204 No Content status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParcel(@PathVariable Long id) {
        parcelService.deleteParcel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
