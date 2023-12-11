// LessonBookingActivity.java

package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LessonBookingActivity extends AppCompatActivity {

    // UI elements
    private Spinner spinnerTeamMember;
    private ListView listViewAvailableTimes;
    private Button buttonBookLesson;
    private Button buttonHome;
    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_booking);

        // Initialize UI elements
        spinnerTeamMember = findViewById(R.id.spinnerTeamMember);
        listViewAvailableTimes = findViewById(R.id.listViewAvailableTimes);
        buttonBookLesson = findViewById(R.id.buttonBookLesson);
        buttonHome = findViewById(R.id.buttonHome);
        buttonBack = findViewById(R.id.buttonBack);

        // Initialize ArrayAdapter for team members spinner
        ArrayAdapter<CharSequence> teamMemberAdapter = ArrayAdapter.createFromResource(
                this, R.array.team_members, android.R.layout.simple_spinner_item);
        teamMemberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTeamMember.setAdapter(teamMemberAdapter);

        // Initialize ArrayAdapter for available times list
        ArrayAdapter<String> availableTimesAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, new ArrayList<>());
        listViewAvailableTimes.setAdapter(availableTimesAdapter);

        // Set listener for team member spinner item selection
        spinnerTeamMember.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updateAvailableTimes(parentView.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        // Set listener for "Book Lesson" button click
        buttonBookLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookLesson();
            }
        });

        // Set listener for "Home" button click
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LessonBookingActivity.this, MainActivity.class));
                finish();
            }
        });

        // Set listener for "Back" button click
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    // Update the available times based on the selected team member
    private void updateAvailableTimes(String selectedTeamMember) {
        List<String> availableTimes = generateRandomTimes();
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) listViewAvailableTimes.getAdapter();
        adapter.clear();
        adapter.addAll(availableTimes);
    }

    // Generate a random list of available times
    private List<String> generateRandomTimes() {
        List<String> times = new ArrayList<>(Arrays.asList("10:00 AM", "2:00 PM", "4:30 PM", "6:00 PM", "8:00 PM"));
        List<String> availableTimes = new ArrayList<>();
        Random random = new Random();
        int numberOfTimes = random.nextInt(times.size()) + 1;
        for (int i = 0; i < numberOfTimes; i++) {
            int randomIndex = random.nextInt(times.size());
            availableTimes.add(times.get(randomIndex));
            times.remove(randomIndex);
        }
        return availableTimes;
    }

    // Book a lesson based on the selected team member and time
    private void bookLesson() {
        String selectedTeamMember = spinnerTeamMember.getSelectedItem().toString();
        int selectedPosition = listViewAvailableTimes.getCheckedItemPosition();

        if (selectedPosition != ListView.INVALID_POSITION) {
            String selectedTime = (String) listViewAvailableTimes.getItemAtPosition(selectedPosition);

            // Display a toast message indicating the booked lesson
            String message = "Lesson booked with " + selectedTeamMember + " at " + selectedTime;
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            // Display a toast message prompting the user to select a time
            Toast.makeText(this, "Please select a time before booking.", Toast.LENGTH_SHORT).show();
        }
    }
}
