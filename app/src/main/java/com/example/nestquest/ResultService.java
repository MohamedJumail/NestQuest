package com.example.nestquest;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ResultService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_service);

        // Retrieve service providers from intent
        ArrayList<ServiceProvider> providers = getIntent().getParcelableArrayListExtra("providers");

        // Create custom adapter to populate ListView with service provider details
        ServiceProviderAdapter adapter = new ServiceProviderAdapter(this, R.layout.activity_service_provider_adapter, providers);

        // Set adapter to ListView
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }
}
