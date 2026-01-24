package com.example.parcelmanagement.service;

import com.example.parcelmanagement.model.Parcel;
import java.util.List;

/**
 * Service interface for Parcel operations
 * Defines business logic methods for parcel management
 */
public interface ParcelService {

    /**
     * Create a new parcel
     * @param parcel Parcel object to be created
     * @return Created parcel with generated ID
     */
    Parcel createParcel(Parcel parcel);

    /**
     * Retrieve all parcels from the database
     * @return List of all parcels
     */
    List<Parcel> getAllParcels();

    /**
     * Get a specific parcel by ID
     * @param id Parcel ID
     * @return Parcel object if found
     */
    Parcel getParcelById(Long id);

    /**
     * Update an existing parcel
     * @param id Parcel ID to update
     * @param parcel Updated parcel data
     * @return Updated parcel object
     */
    Parcel updateParcel(Long id, Parcel parcel);

    /**
     * Delete a parcel by ID
     * @param id Parcel ID to delete
     */
    void deleteParcel(Long id);
}
