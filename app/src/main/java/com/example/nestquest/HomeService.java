package com.example.nestquest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeService extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_service);
        Button b1,b2;
        b1=(Button) findViewById(R.id.Provide);
        b2=(Button) findViewById(R.id.request);
        b1.setOnClickListener(new View.OnClickListener() {


            @SuppressLint("ResourceType")
            public void onClick(View v) {
                b1.setTextColor(getResources().getColor(R.drawable.button_clicked));
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.Homefragment, ProvideService.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();


            }
        });
        b2.setOnClickListener(new View.OnClickListener() {


            @SuppressLint("ResourceType")
            public void onClick(View v) {
                b2.setTextColor(getResources().getColor(R.drawable.button_clicked));
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.Homefragment, RequestService.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();


            }
        });

    }
}