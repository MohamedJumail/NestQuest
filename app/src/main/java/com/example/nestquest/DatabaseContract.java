package com.example.nestquest;


import android.provider.BaseColumns;


public final class DatabaseContract {
    // Private constructor to prevent instantiation
    private DatabaseContract() {}

    /* Inner class that defines the table contents */
    public static class UserProfileEntry implements BaseColumns {
        public static final String TABLE_NAME = "user_profiles";
        public static final String COLUMN_FULL_NAME = "full_name";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_AADHAR = "aadhar";
        public static final String COLUMN_CONTACT_TIME = "contact_time";
        public static final String COLUMN_RENT = "rent";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_NUMBER_OF_ROOMS = "number_of_rooms";
        public static final String COLUMN_AREA = "area";
        public static final String COLUMN_ATTACHED_BATHROOM = "attached_bathroom";
        public static final String COLUMN_PARKING_FACILITY = "parking_facility";
        public static final String COLUMN_WIFI_FACILITY = "wifi_facility";
        public static final String COLUMN_STOREY = "storey";
        public static final String COLUMN_HOUSING_TYPE = "housing_type";
    }
}

