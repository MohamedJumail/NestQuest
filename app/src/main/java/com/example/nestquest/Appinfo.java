package com.example.nestquest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Appinfo extends AppCompatActivity {
    private EditText searchEditText;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appinfo);

        // Initialize EditText and Button
        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);

        // Set OnClickListener for the search button
        searchButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                if (!isNetworkAvailable()) {
                    showNoInternetConnectionDialog();
                }
                searchButton.setTextColor(getResources().getColor(R.drawable.button_clicked));
                // Get the text from the EditText
                String searchText = searchEditText.getText().toString();

                // Construct the search URL for homes for rent
                String searchUrl = "https://www.google.com/search?q=" + searchText + " homes for rent";

                // Create an Intent to open the URL in a web browser
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(searchUrl));

                // Verify that there is an activity to handle the intent
                if (intent.resolveActivity(getPackageManager()) != null) {
                    // Start the activity
                    startActivity(intent);
                }
            }
        });
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }

    private void showNoInternetConnectionDialog() {
        // Create a custom view for the dialog
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.broadcast, null);

        // Set the message text
        TextView textViewMessage = dialogView.findViewById(R.id.textViewMessage);
        if (textViewMessage != null) {
            textViewMessage.setText("No Internet Connection");
        }

        // Create AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        final AlertDialog dialog = builder.create();
        dialog.show();
    }
}



