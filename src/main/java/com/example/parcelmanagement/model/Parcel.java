package com.example.parcelmanagement.model;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entity class representing a Parcel in the system
 * Stores parcel information including sender, receiver, and status
 */
@Entity
@Table(name = "parcels")
public class Parcel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String senderName;

    @Column(nullable = false)
    private String receiverName;

    @Column(nullable = false)
    private String parcelDescription;

    @Column(nullable = false)
    private LocalDate receivedDate;

    @Column(nullable = false)
    private String status; // RECEIVED, DELIVERED, PENDING

    @Column
    private String contactNumber; // optional field

    // Default constructor
    public Parcel() {
    }

    // Parameterized constructor
    public Parcel(String senderName, String receiverName, String parcelDescription, 
                  LocalDate receivedDate, String status, String contactNumber) {
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.parcelDescription = parcelDescription;
        this.receivedDate = receivedDate;
        this.status = status;
        this.contactNumber = contactNumber;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getParcelDescription() {
        return parcelDescription;
    }

    public void setParcelDescription(String parcelDescription) {
        this.parcelDescription = parcelDescription;
    }

    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "id=" + id +
                ", senderName='" + senderName + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", parcelDescription='" + parcelDescription + '\'' +
                ", receivedDate=" + receivedDate +
                ", status='" + status + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}
