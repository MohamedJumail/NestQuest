package com.example.nestquest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.slider.RangeSlider;

import java.util.ArrayList;
import java.util.List;

public class Searchhome extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;
    RangeSlider rangeSlider;
    EditText search;
    ScrollView scrollView;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchhome);

        // Check and request location permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        }

        scrollView = findViewById(R.id.scrollview3);
        search = findViewById(R.id.search);

        // Set OnClickListener on EditText to toggle ScrollView visibility
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scrollView.getVisibility() == View.VISIBLE) {
                    scrollView.setVisibility(View.GONE);
                } else {
                    scrollView.setVisibility(View.VISIBLE);
                }
            }
        });

        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.areas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        rangeSlider = findViewById(R.id.rangeslider1);
        rangeSlider.setValueFrom(0);
        rangeSlider.setValueTo(100000);
        rangeSlider.setCustomThumbDrawable(R.drawable.thumb);
        rangeSlider.setTrackActiveTintList(getResources().getColorStateList(R.color.yellow));
        rangeSlider.addOnChangeListener((slider, value, fromUser) -> {
            // You can handle slider changes here
        });

        Button submitButton = findViewById(R.id.btnSubmit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchUserProfile();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {

                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Searchhome.this, dashboard.class);
        startActivity(intent);
        finish();
    }

    private void searchUserProfile() {
        // Retrieve user-selected values
        String housingType = getSelectedHousingType();
        float minRentFloat = rangeSlider.getValues().get(0); // Get the float value
        float maxRentFloat = rangeSlider.getValues().get(1); // Get the float value
        int minRent = Math.round(minRentFloat); // Convert float to int
        int maxRent = Math.round(maxRentFloat); // Convert float to int

        String selectedArea = ((Spinner) findViewById(R.id.spinner1)).getSelectedItem().toString();

        // Perform database query to search for matching records
        List<UserProfile1> searchResults = new ArrayList<>();
        SQLiteDatabase db = new MyDatabaseHelper(this).getReadableDatabase();
        String[] projection = {
                DatabaseContract.UserProfileEntry.COLUMN_FULL_NAME,
                DatabaseContract.UserProfileEntry.COLUMN_PHONE,
                DatabaseContract.UserProfileEntry.COLUMN_HOUSING_TYPE,
                DatabaseContract.UserProfileEntry.COLUMN_RENT,
                DatabaseContract.UserProfileEntry.COLUMN_AREA,
                DatabaseContract.UserProfileEntry.COLUMN_ADDRESS, // Include address field
                DatabaseContract.UserProfileEntry.COLUMN_NUMBER_OF_ROOMS
        };
        String selection = DatabaseContract.UserProfileEntry.COLUMN_HOUSING_TYPE + " = ? AND " +
                DatabaseContract.UserProfileEntry.COLUMN_RENT + " BETWEEN ? AND ? AND " +
                DatabaseContract.UserProfileEntry.COLUMN_AREA + " = ?";
        String[] selectionArgs = {housingType, String.valueOf(minRent), String.valueOf(maxRent), selectedArea};
        Cursor cursor = db.query(
                DatabaseContract.UserProfileEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        // Iterate through the cursor and populate search results
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.UserProfileEntry.COLUMN_FULL_NAME));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.UserProfileEntry.COLUMN_PHONE));
            String type = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.UserProfileEntry.COLUMN_HOUSING_TYPE));
            int rent = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.UserProfileEntry.COLUMN_RENT));
            String area = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.UserProfileEntry.COLUMN_AREA));
            String address = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.UserProfileEntry.COLUMN_ADDRESS)); // Retrieve address field
            int rooms = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.UserProfileEntry.COLUMN_NUMBER_OF_ROOMS));

            // Create a UserProfile1 object with the retrieved data
            UserProfile1 userProfile1 = new UserProfile1(name, phone, type, rent, area, address, rooms);
            searchResults.add(userProfile1);
        }
        cursor.close();
        db.close();

        // Pass searchResults to the result activity
        Intent intent = new Intent(Searchhome.this, Result.class);
        intent.putParcelableArrayListExtra("searchResults", (ArrayList<? extends Parcelable>) searchResults);
        startActivity(intent);
    }

    // Helper method to get the selected housing type from radio button
    private String getSelectedHousingType() {
        RadioGroup radioGroup = findViewById(R.id.storey);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectedId);
        return radioButton.getText().toString();
    }
}
