package com.example.nestquest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

public class RequestService extends Fragment implements View.OnClickListener {

    private Spinner spinnerServiceRequested;
    private EditText editTextFullName;
    private EditText editTextAddress;
    private Button btnSubmit;

    public RequestService() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_request_service, container, false);

        // Find views
        spinnerServiceRequested = rootView.findViewById(R.id.spinnerServiceRequested);
        editTextFullName = rootView.findViewById(R.id.editTextFullName);
        editTextAddress = rootView.findViewById(R.id.editTextAddress);
        btnSubmit = rootView.findViewById(R.id.btnSubmit);

        // Set click listener for the submit button
        btnSubmit.setOnClickListener(this);

        // Define the array of service types
        String[] serviceTypes = {"Home cleaning", "Bathroom cleaning", "AC repair", "Electrical Works", "Plumbing"};

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, serviceTypes);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinnerServiceRequested.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSubmit) {
            String requestedService = spinnerServiceRequested.getSelectedItem().toString();
            String requestedPersonName = editTextFullName.getText().toString();
            String requestedPersonAddress = editTextAddress.getText().toString();
            fetchServiceProviders(requestedService, requestedPersonName, requestedPersonAddress);
        }
    }

    private void fetchServiceProviders(String requestedService, String requestedPersonName, String requestedPersonAddress) {
        // Query database to fetch service providers for the requested service
        // This code is similar to the one used in ResultService activity

        ArrayList<ServiceProvider> providers = new ArrayList<>();

        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                ServiceProviderContract.ServiceProviderEntry.COLUMN_FULL_NAME,
                ServiceProviderContract.ServiceProviderEntry.COLUMN_CONTACT_NUMBER,
                ServiceProviderContract.ServiceProviderEntry.COLUMN_CHARGE
        };
        String selection = ServiceProviderContract.ServiceProviderEntry.COLUMN_SERVICE_PROVIDED + "=?";
        String[] selectionArgs = { requestedService };

        Cursor cursor = db.query(
                ServiceProviderContract.ServiceProviderEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String fullName = cursor.getString(cursor.getColumnIndexOrThrow(
                        ServiceProviderContract.ServiceProviderEntry.COLUMN_FULL_NAME));
                String contactNumber = cursor.getString(cursor.getColumnIndexOrThrow(
                        ServiceProviderContract.ServiceProviderEntry.COLUMN_CONTACT_NUMBER));
                double charge = cursor.getDouble(cursor.getColumnIndexOrThrow(
                        ServiceProviderContract.ServiceProviderEntry.COLUMN_CHARGE));

                ServiceProvider provider = new ServiceProvider();
                provider.setFullName(fullName);
                provider.setContactNumber(contactNumber);
                provider.setServiceProvided(requestedService);
                provider.setCharge(charge);

                // Set requested person's name and address
                provider.setRequestedPersonName(requestedPersonName);
                provider.setRequestedPersonAddress(requestedPersonAddress);

                providers.add(provider);
            }
            cursor.close();
        }

        db.close();

        // Pass the fetched providers to the ResultService activity
        Intent intent = new Intent(getContext(), ResultService.class);
        intent.putParcelableArrayListExtra("providers", providers);
        startActivity(intent);
    }
}
