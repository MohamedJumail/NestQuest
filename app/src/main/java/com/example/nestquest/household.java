package com.example.nestquest;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class household extends Fragment implements View.OnClickListener {

    private EditText etRent, etAddress, etNoRooms, etArea;
    private CheckBox cbAttachedBathroom, cbParkingLot, cbWifiFacility;
    private RadioGroup radioGroupStorey, radioGroupHousing;

    // UserProfile object to hold combined information
    private UserProfile userProfile;
    private Button submitButton;

    public household() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_household, container, false);

        etRent = v.findViewById(R.id.rent);
        etAddress = v.findViewById(R.id.address);
        etNoRooms = v.findViewById(R.id.no_rooms);
        etArea = v.findViewById(R.id.area);
        cbAttachedBathroom = v.findViewById(R.id.attached_bathroom);
        cbParkingLot = v.findViewById(R.id.Parking_lot);
        cbWifiFacility = v.findViewById(R.id.Water_facility);
        radioGroupStorey = v.findViewById(R.id.storey);
        radioGroupHousing = v.findViewById(R.id.housing);

        Button submitButton = v.findViewById(R.id.btnSubmit);
        submitButton.setOnClickListener(this);

        // Initialize UserProfile object
        userProfile = new UserProfile();

        // Retrieve data from Description fragment
        Bundle bundle = getArguments();
        if (bundle != null) {
            // Personal information
            userProfile.setFullName(bundle.getString("fullName"));
            userProfile.setEmail(bundle.getString("email"));
            userProfile.setPhone(bundle.getString("phone"));
            userProfile.setAadhar(bundle.getString("aadhar"));
            userProfile.setContactTime(bundle.getString("contactTime"));
        }
        TextView textViewWelcome = v.findViewById(R.id.datafrom1);
        textViewWelcome.setText("Welcome, " + userProfile.getFullName()+"! Now Enter your Household Informations");

        return v;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {
        submitButton.setTextColor(getResources().getColor(R.drawable.button_clicked));
        if (v.getId() == R.id.btnSubmit) {
            // Household information
            String rent = etRent.getText().toString().trim();
            String address = etAddress.getText().toString().trim();
            String noRooms = etNoRooms.getText().toString().trim();
            String area = etArea.getText().toString().trim();

            if (rent.isEmpty() || address.isEmpty() || noRooms.isEmpty() || area.isEmpty()) {
                // If any field is empty, show a toast message
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            userProfile.setRent(rent);
            userProfile.setAddress(address);
            userProfile.setNumberOfRooms(noRooms);
            userProfile.setArea(area);
            userProfile.setAttachedBathroom(cbAttachedBathroom.isChecked());
            userProfile.setParkingFacility(cbParkingLot.isChecked());
            userProfile.setWifiFacility(cbWifiFacility.isChecked());
            userProfile.setStorey(((RadioButton) getView().findViewById(radioGroupStorey.getCheckedRadioButtonId())).getText().toString());
            userProfile.setHousingType(((RadioButton) getView().findViewById(radioGroupHousing.getCheckedRadioButtonId())).getText().toString());

            // Save data to database
            saveToDatabase(userProfile);

            // Inform user about successful submission
            Toast.makeText(getContext(), "Information saved successfully", Toast.LENGTH_SHORT).show();
        }
    }


    private void saveToDatabase(UserProfile userProfile) {
        // Get writable database
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create ContentValues object to store data
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.UserProfileEntry.COLUMN_FULL_NAME, userProfile.getFullName());
        values.put(DatabaseContract.UserProfileEntry.COLUMN_EMAIL, userProfile.getEmail());
        values.put(DatabaseContract.UserProfileEntry.COLUMN_PHONE, userProfile.getPhone());
        values.put(DatabaseContract.UserProfileEntry.COLUMN_AADHAR, userProfile.getAadhar());
        values.put(DatabaseContract.UserProfileEntry.COLUMN_CONTACT_TIME, userProfile.getContactTime());
        values.put(DatabaseContract.UserProfileEntry.COLUMN_RENT, userProfile.getRent());
        values.put(DatabaseContract.UserProfileEntry.COLUMN_ADDRESS, userProfile.getAddress());
        values.put(DatabaseContract.UserProfileEntry.COLUMN_NUMBER_OF_ROOMS, userProfile.getNumberOfRooms());
        values.put(DatabaseContract.UserProfileEntry.COLUMN_AREA, userProfile.getArea());
        values.put(DatabaseContract.UserProfileEntry.COLUMN_ATTACHED_BATHROOM, userProfile.isAttachedBathroom() ? 1 : 0);
        values.put(DatabaseContract.UserProfileEntry.COLUMN_PARKING_FACILITY, userProfile.isParkingFacility() ? 1 : 0);
        values.put(DatabaseContract.UserProfileEntry.COLUMN_WIFI_FACILITY, userProfile.isWifiFacility() ? 1 : 0);
        values.put(DatabaseContract.UserProfileEntry.COLUMN_STOREY, userProfile.getStorey());
        values.put(DatabaseContract.UserProfileEntry.COLUMN_HOUSING_TYPE, userProfile.getHousingType());

        // Insert data into the database
        long newRowId = db.insert(DatabaseContract.UserProfileEntry.TABLE_NAME, null, values);

        // Close the database connection
        db.close();
    }
}
