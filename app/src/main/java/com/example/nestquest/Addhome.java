package com.example.nestquest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Addhome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addhome);
        Button b1,b2,b3;
        b1=(Button) findViewById(R.id.button4);
        b2=(Button) findViewById(R.id.button5);
        b3=(Button) findViewById(R.id.button6);
        b1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                b1.setTextColor(getResources().getColor(R.drawable.button_clicked));
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.fragmentContainerView2, Description.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();


            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                b2.setTextColor(getResources().getColor(R.drawable.button_clicked));
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.fragmentContainerView2,household.class,null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                b3.setTextColor(getResources().getColor(R.drawable.button_clicked));
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.fragmentContainerView2, Image.class,null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();
            }
        });





    }

    @Override
    public void onBackPressed() {
        // Handle the back button press
        // Here, we navigate back to the Dashboard activity
        Intent intent = new Intent(Addhome.this, dashboard.class);
        startActivity(intent);
        finish();  // Optional: finish the current activity so that it is removed from the stack
    }
}
