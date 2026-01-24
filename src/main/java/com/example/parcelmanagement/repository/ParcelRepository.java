package com.example.parcelmanagement.repository;

import com.example.parcelmanagement.model.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Parcel entity
 * Provides CRUD operations through JpaRepository
 */
@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Long> {
    // JpaRepository provides all basic CRUD methods
    // Additional custom queries can be added here if needed
}
