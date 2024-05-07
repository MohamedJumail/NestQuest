package com.example.nestquest;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import com.example.nestquest.UserProfile1;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result2);

        // Retrieve the search results passed from the Searchhome activity
        ArrayList<UserProfile1> searchResults = getIntent().getParcelableArrayListExtra("searchResults");

        // Create a custom adapter to populate the ListView with the search results
        ResultAdapter adapter = new ResultAdapter(this, R.layout.list_item_result, searchResults);

        // Set the adapter to the ListView
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }
}
