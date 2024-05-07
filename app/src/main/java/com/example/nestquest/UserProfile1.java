package com.example.nestquest;

import android.os.Parcel;
import android.os.Parcelable;

public class UserProfile1 implements Parcelable {
    private String name;
    private String phone;
    private String type;
    private int rent;
    private String area;
    private String address; // Include address field
    private int rooms;

    // Default constructor required by Parcelable
    public UserProfile1() {
    }

    // Constructor with parameters
    public UserProfile1(String name, String phone, String type, int rent, String area, String address, int rooms) {
        this.name = name;
        this.phone = phone;
        this.type = type;
        this.rent = rent;
        this.area = area;
        this.address = address;
        this.rooms = rooms;
    }

    protected UserProfile1(Parcel in) {
        name = in.readString();
        phone = in.readString();
        type = in.readString();
        rent = in.readInt();
        area = in.readString();
        address = in.readString();
        rooms = in.readInt();
    }

    public static final Creator<UserProfile1> CREATOR = new Creator<UserProfile1>() {
        @Override
        public UserProfile1 createFromParcel(Parcel in) {
            return new UserProfile1(in);
        }

        @Override
        public UserProfile1[] newArray(int size) {
            return new UserProfile1[size];
        }
    };

    public String getFullName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeString(type);
        dest.writeInt(rent);
        dest.writeString(area);
        dest.writeString(address);
        dest.writeInt(rooms);
    }
}
