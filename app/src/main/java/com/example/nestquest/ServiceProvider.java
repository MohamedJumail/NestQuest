package com.example.nestquest;

import android.os.Parcel;
import android.os.Parcelable;

public class ServiceProvider implements Parcelable {
    private String fullName;
    private String requestedPersonName;
    private String address;
    private String contactNumber;
    private String serviceProvided;
    private double charge;

    public ServiceProvider() {
    }

    protected ServiceProvider(Parcel in) {
        fullName = in.readString();
        requestedPersonName = in.readString();
        address = in.readString();
        contactNumber = in.readString();
        serviceProvided = in.readString();
        charge = in.readDouble();
    }

    public static final Creator<ServiceProvider> CREATOR = new Creator<ServiceProvider>() {
        @Override
        public ServiceProvider createFromParcel(Parcel in) {
            return new ServiceProvider(in);
        }

        @Override
        public ServiceProvider[] newArray(int size) {
            return new ServiceProvider[size];
        }
    };

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRequestedPersonName() {
        return requestedPersonName;
    }

    public void setRequestedPersonName(String requestedPersonName) {
        this.requestedPersonName = requestedPersonName;
    }

    public String getAddress() {
        return address;
    }

    public void setRequestedPersonAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getServiceProvided() {
        return serviceProvided;
    }

    public void setServiceProvided(String serviceProvided) {
        this.serviceProvided = serviceProvided;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    @Override
    public String toString() {
        return "ServiceProvider{" +
                "fullName='" + fullName + '\'' +
                ", requestedPersonName='" + requestedPersonName + '\'' +
                ", address='" + address + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", serviceProvided='" + serviceProvided + '\'' +
                ", charge=" + charge +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fullName);
        dest.writeString(requestedPersonName);
        dest.writeString(address);
        dest.writeString(contactNumber);
        dest.writeString(serviceProvided);
        dest.writeDouble(charge);
    }
}
