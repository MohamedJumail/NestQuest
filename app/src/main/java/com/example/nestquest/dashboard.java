package com.example.nestquest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        CardView c1, c2, c3,c4;
        c1 = findViewById(R.id.cardView1);
        c2 = findViewById(R.id.cardView2);
        c3 = findViewById(R.id.cardView3);
        c4 = findViewById(R.id.cardView4);


        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dashboard.this, Addhome.class);
                startActivity(i);
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dashboard.this, Searchhome.class);
                startActivity(i);
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dashboard.this, HomeService.class);
                startActivity(i);
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dashboard.this, Appinfo.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Handle the back button press
        // Here, we navigate back to the MainActivity
        Intent intent = new Intent(dashboard.this, MainActivity.class);
        startActivity(intent);
        finish();  // Optional: finish the current activity so that it is removed from the stack
    }
}
