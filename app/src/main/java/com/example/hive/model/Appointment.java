package com.example.hive.model;

public class Appointment {
    private String createdAt;
    private String userId;
    private String bookedBy;
    private String appointmentDate;
    private String status;
    private String message;
    private String username;
    public String bookedByUserName;
    private String userServiceType;

    public Appointment(){

    }

    public String getBookedByUserName() {
        return bookedByUserName;
    }

    public void setBookedByUserName(String bookedByUserName) {
        this.bookedByUserName = bookedByUserName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserServiceType() {
        return userServiceType;
    }

    public void setUserServiceType(String userServiceType) {
        this.userServiceType = userServiceType;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(String bookedBy) {
        this.bookedBy = bookedBy;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
