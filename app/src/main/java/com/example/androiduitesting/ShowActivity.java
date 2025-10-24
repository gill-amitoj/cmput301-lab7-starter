package com.example.androiduitesting;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        // Get the TextView and Button from the layout
        TextView cityNameTextView = findViewById(R.id.textView_cityName);
        Button backButton = findViewById(R.id.button_back);

        // Get the Intent that started this activity
        Intent intent = getIntent();
        // Get the city name passed from MainActivity, with a default value
        String cityName = intent.getStringExtra("CITY_NAME");

        // Set the city name to the TextView
        if (cityName != null && !cityName.isEmpty()) {
            cityNameTextView.setText(cityName);
        }

        // Set a listener on the back button to finish the activity
        backButton.setOnClickListener(v -> {
            finish(); // Closes this activity and returns to the previous one
        });
    }
}
