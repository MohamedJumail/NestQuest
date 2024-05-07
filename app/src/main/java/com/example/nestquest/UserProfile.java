package com.example.nestquest;



import java.io.Serializable;

public class UserProfile implements Serializable {
    private String fullName;
    private String email;
    private String phone;
    private String aadhar;
    private String contactTime;
    private String rent;
    private String address;
    private String numberOfRooms;
    private String area;
    private boolean attachedBathroom;
    private boolean parkingFacility;
    private boolean wifiFacility;
    private String storey;
    private String housingType;

    public UserProfile() {
        // Default constructor
    }

    // Getters and setters for all fields

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getContactTime() {
        return contactTime;
    }

    public void setContactTime(String contactTime) {
        this.contactTime = contactTime;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(String numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public boolean isAttachedBathroom() {
        return attachedBathroom;
    }

    public void setAttachedBathroom(boolean attachedBathroom) {
        this.attachedBathroom = attachedBathroom;
    }

    public boolean isParkingFacility() {
        return parkingFacility;
    }

    public void setParkingFacility(boolean parkingFacility) {
        this.parkingFacility = parkingFacility;
    }

    public boolean isWifiFacility() {
        return wifiFacility;
    }

    public void setWifiFacility(boolean wifiFacility) {
        this.wifiFacility = wifiFacility;
    }

    public String getStorey() {
        return storey;
    }

    public void setStorey(String storey) {
        this.storey = storey;
    }

    public String getHousingType() {
        return housingType;
    }

    public void setHousingType(String housingType) {
        this.housingType = housingType;
    }
}

