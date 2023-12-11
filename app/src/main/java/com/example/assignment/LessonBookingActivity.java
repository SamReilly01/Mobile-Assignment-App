package com.example.assignment;

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

    private Spinner spinnerTeamMember;
    private ListView listViewAvailableTimes;
    private Button buttonBookLesson;

    private List<String> generatedTimesTeam1; // Store the generated times for Team Member 1
    private List<String> generatedTimesTeam2; // Store the generated times for Team Member 2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_booking);

        spinnerTeamMember = findViewById(R.id.spinnerTeamMember);
        listViewAvailableTimes = findViewById(R.id.listViewAvailableTimes);
        buttonBookLesson = findViewById(R.id.buttonBookLesson);

        // Set up team member spinner
        ArrayAdapter<CharSequence> teamMemberAdapter = ArrayAdapter.createFromResource(
                this, R.array.team_members, android.R.layout.simple_spinner_item);
        teamMemberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTeamMember.setAdapter(teamMemberAdapter);

        // Set up available times list (empty initially)
        ArrayAdapter<String> availableTimesAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, new ArrayList<>());
        listViewAvailableTimes.setAdapter(availableTimesAdapter);

        generatedTimesTeam1 = null; // Initialize as null
        generatedTimesTeam2 = null; // Initialize as null

        // Set up event listener for team member selection
        spinnerTeamMember.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Update available times based on selected team member
                updateAvailableTimes(parentView.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        // Set up event listener for book lesson button
        buttonBookLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implement the logic for booking the lesson
                bookLesson();
            }
        });
    }

    private void updateAvailableTimes(String selectedTeamMember) {
        // Generate random available times only if not generated before
        if (selectedTeamMember.equals("Tiger Woods") && generatedTimesTeam1 == null) {
            generatedTimesTeam1 = generateRandomTimes();
        } else if (selectedTeamMember.equals("Min Woo Lee") && generatedTimesTeam2 == null) {
            generatedTimesTeam2 = generateRandomTimes();
        }

        // Update the list view with available times based on the selected team member
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) listViewAvailableTimes.getAdapter();
        adapter.clear();
        if (selectedTeamMember.equals("Tiger Woods")) {
            adapter.addAll(generatedTimesTeam1);
        } else if (selectedTeamMember.equals("Min Woo Lee")) {
            adapter.addAll(generatedTimesTeam2);
        }
    }

    private List<String> generateRandomTimes() {
        // Generate some random times (for demonstration purposes)
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

    private void bookLesson() {
        // Implement the logic for booking the lesson
        String selectedTeamMember = spinnerTeamMember.getSelectedItem().toString();
        int selectedPosition = listViewAvailableTimes.getCheckedItemPosition();

        if (selectedPosition != ListView.INVALID_POSITION) {
            String selectedTime = (String) listViewAvailableTimes.getItemAtPosition(selectedPosition);

            // Display a toast with the booking details
            String message = "Lesson booked with " + selectedTeamMember + " at " + selectedTime;
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            // Handle the case where no time is selected
            Toast.makeText(this, "Please select a time before booking.", Toast.LENGTH_SHORT).show();
        }
    }
}
