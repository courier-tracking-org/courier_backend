package com.example.courier_tracking.service.impl;

import com.example.courier_tracking.model.Parcel;
import com.example.courier_tracking.repository.ParcelRepository;
import com.example.courier_tracking.service.ParcelService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of ParcelService interface
 * Contains business logic for parcel management operations
 */
@Service
public class ParcelServiceImpl implements ParcelService {

    private final ParcelRepository parcelRepository;

    // Constructor injection for repository
    public ParcelServiceImpl(ParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    /**
     * Create a new parcel in the database
     */
    @Override
    public Parcel createParcel(Parcel parcel) {
        // Save the parcel and return the saved entity with generated ID
        return parcelRepository.save(parcel);
    }

    /**
     * Retrieve all parcels from the database
     */
    @Override
    public List<Parcel> getAllParcels() {
        // Fetch all parcels using findAll() method
        return parcelRepository.findAll();
    }

    /**
     * Get a parcel by its ID
     * Throws exception if parcel not found
     */
    @Override
    public Parcel getParcelById(Long id) {
        // Find parcel by ID or throw exception if not found
        return parcelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parcel not found with id: " + id));
    }

    /**
     * Update an existing parcel
     * First finds the parcel, then updates its fields
     */
    @Override
    public Parcel updateParcel(Long id, Parcel parcel) {
        // Check if parcel exists
        Parcel existingParcel = getParcelById(id);
        
        // Update fields
        existingParcel.setSenderName(parcel.getSenderName());
        existingParcel.setReceiverName(parcel.getReceiverName());
        existingParcel.setParcelDescription(parcel.getParcelDescription());
        existingParcel.setReceivedDate(parcel.getReceivedDate());
        existingParcel.setStatus(parcel.getStatus());
        existingParcel.setContactNumber(parcel.getContactNumber());
        
        // Save and return updated parcel
        return parcelRepository.save(existingParcel);
    }

    /**
     * Delete a parcel by ID
     * Verifies parcel exists before deletion
     */
    @Override
    public void deleteParcel(Long id) {
        // Check if parcel exists
        Parcel parcel = getParcelById(id);
        
        // Delete the parcel
        parcelRepository.delete(parcel);
    }
}
