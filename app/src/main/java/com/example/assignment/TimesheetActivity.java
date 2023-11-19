package com.example.assignment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TimesheetActivity extends AppCompatActivity {

    private List<String> availableTimes;
    private List<String> bookedTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timesheet);

        // Initialize available and booked times
        availableTimes = generateAvailableTimes();
        bookedTimes = new ArrayList<>();

        // Find views
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextTime = findViewById(R.id.editTextTime);
        EditText editTextPlayers = findViewById(R.id.editTextPlayers);
        Button buttonSubmit = findViewById(R.id.buttonSubmit);
        TextView textViewAvailableTimes = findViewById(R.id.textViewAvailableTimes);

        // Display initial available times
        textViewAvailableTimes.setText("Available Times:\n" + getAvailableTimesAsString());

        // Handle button click
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve entered values
                String name = editTextName.getText().toString();
                String time = editTextTime.getText().toString();
                String players = editTextPlayers.getText().toString();

                // Perform further processing or validation as needed
                // For now, you can print the values
                System.out.println("Name: " + name);
                System.out.println("Time: " + time);
                System.out.println("Players: " + players);

                // Update available and booked times
                updateTimes(time);

                // Display updated available times
                textViewAvailableTimes.setText("Available Times:\n" + getAvailableTimesAsString());
            }
        });
    }

    // Method to generate a list of available times
    private List<String> generateAvailableTimes() {
        List<String> times = new ArrayList<>();
        // Generate some sample times for demonstration purposes
        times.add("10:00 AM");
        times.add("2:30 PM");
        times.add("4:45 PM");
        times.add("9:15 AM");
        times.add("1:00 PM");
        return times;
    }

    // Method to update available and booked times based on the selected time
    private void updateTimes(String selectedTime) {
        // For simplicity, remove the selected time from available times and add it to booked times
        availableTimes.remove(selectedTime);
        bookedTimes.add(selectedTime);
    }

    // Method to get available times as a formatted string
    private String getAvailableTimesAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String time : availableTimes) {
            stringBuilder.append(time).append("\n");
        }
        return stringBuilder.toString();
    }
}
