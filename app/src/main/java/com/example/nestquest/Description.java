package com.example.nestquest;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.TextAppearanceInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Description extends Fragment implements View.OnClickListener {

    private EditText fullNameEditText, emailEditText, phoneEditText, aadharEditText;
    private RadioGroup contactTimeRadioGroup;
    private Button submitButton;

    public Description() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_description, container, false);

        fullNameEditText = view.findViewById(R.id.editTextFullName);
        emailEditText = view.findViewById(R.id.editTextEmail);
        phoneEditText = view.findViewById(R.id.ph_no);
        aadharEditText = view.findViewById(R.id.Aadhar_no);
        contactTimeRadioGroup = view.findViewById(R.id.radioGroupGender);

        Button submitButton = view.findViewById(R.id.btnSubmit);
        submitButton.setOnClickListener(this);

        return view;
    }

    @SuppressLint("ResourceType")
    @Override

    public void onClick(View v) {
        if (v.getId() == R.id.btnSubmit) {
            submitButton.setTextColor(getResources().getColor(R.drawable.button_clicked));
            String fullName = fullNameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String phone = phoneEditText.getText().toString().trim();
            String aadhar = aadharEditText.getText().toString().trim();
            String contactTime = ((RadioButton) getView().findViewById(contactTimeRadioGroup.getCheckedRadioButtonId())).getText().toString().trim();

            // Check if any field is empty
            if (fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || aadhar.isEmpty() || contactTime.isEmpty()) {
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create bundle and pass data to HouseholdFragment
            Bundle bundle = new Bundle();
            bundle.putString("fullName", fullName);
            bundle.putString("email", email);
            bundle.putString("phone", phone);
            bundle.putString("aadhar", aadhar);
            bundle.putString("contactTime", contactTime);

            // Navigate to HouseholdFragment
            household householdFragment = new household();
            householdFragment.setArguments(bundle);
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainerView2, householdFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

}
