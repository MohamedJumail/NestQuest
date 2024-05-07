package com.example.nestquest;

import android.provider.BaseColumns;

public final class ServiceProviderContract {
    private ServiceProviderContract() {}

    public static class ServiceProviderEntry implements BaseColumns {
        public static final String TABLE_NAME = "service_providers";
        public static final String COLUMN_FULL_NAME = "full_name";
        public static final String COLUMN_CONTACT_NUMBER = "contact_number";
        public static final String COLUMN_CHARGE = "charge";
        public static final String COLUMN_SERVICE_PROVIDED = "service_provided";
    }
}

