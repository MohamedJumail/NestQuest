package com.example.nestquest;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProvideService extends Fragment implements View.OnClickListener {

    private EditText editTextFullName, editTextContactNumber, editTextCharge;
    private Spinner spinnerServiceProvided;
    private Button btnSubmit;
    private DatabaseHelper dbHelper;

    public ProvideService() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DatabaseHelper(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_provide_service, container, false);

        editTextFullName = rootView.findViewById(R.id.editTextFullName);
        editTextContactNumber = rootView.findViewById(R.id.editTextContactNumber);
        editTextCharge = rootView.findViewById(R.id.editTextCharge);
        spinnerServiceProvided = rootView.findViewById(R.id.spinnerServiceProvided);
        btnSubmit = rootView.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);

        String[] serviceTypes = {"Home cleaning", "Bathroom cleaning", "AC repair", "Electrical Works", "Plumbing"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, serviceTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerServiceProvided.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSubmit) {
            insertServiceDetails();
        }
    }

    private void insertServiceDetails() {
        String fullName = editTextFullName.getText().toString().trim();
        String contactNumber = editTextContactNumber.getText().toString().trim();
        double charge = Double.parseDouble(editTextCharge.getText().toString());
        String serviceProvided = spinnerServiceProvided.getSelectedItem().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ServiceProviderContract.ServiceProviderEntry.COLUMN_FULL_NAME, fullName);
        values.put(ServiceProviderContract.ServiceProviderEntry.COLUMN_CONTACT_NUMBER, contactNumber);
        values.put(ServiceProviderContract.ServiceProviderEntry.COLUMN_CHARGE, charge);
        values.put(ServiceProviderContract.ServiceProviderEntry.COLUMN_SERVICE_PROVIDED, serviceProvided);

        long newRowId = db.insert(ServiceProviderContract.ServiceProviderEntry.TABLE_NAME, null, values);
        db.close();

        if (newRowId != -1) {
            Toast.makeText(requireContext(), "Service details inserted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(requireContext(), "Failed to insert service details", Toast.LENGTH_SHORT).show();
        }
    }
}
