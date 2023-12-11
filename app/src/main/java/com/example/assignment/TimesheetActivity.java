// TimesheetActivity.java

package com.example.assignment;

import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import com.example.assignment.database.JournalEntryDatabase;
import com.example.assignment.database.entities.JournalEntry;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class TimesheetActivity extends AppCompatActivity {

    // UI elements
    private EditText journalEntry;
    private Button saveEntryButton;
    private TextView challengeOfTheDay;
    private TextView lastSavedTime;
    private Button viewEntriesButton;
    private Button buttonHome;
    private Button buttonBack;

    // Date format for timestamp
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    // Room Database instance
    private JournalEntryDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timesheet);

        // Initialize UI elements
        journalEntry = findViewById(R.id.journalEntry);
        saveEntryButton = findViewById(R.id.saveEntryButton);
        challengeOfTheDay = findViewById(R.id.challengeOfTheDay);
        lastSavedTime = findViewById(R.id.lastSavedTime);
        viewEntriesButton = findViewById(R.id.viewEntriesButton);
        buttonHome = findViewById(R.id.buttonHome);
        buttonBack = findViewById(R.id.buttonBack);

        // Set Title Text
        String todayChallenge = getDailyChallenge();
        challengeOfTheDay.setText(todayChallenge);

        // Initialize Room Database
        db = Room.databaseBuilder(getApplicationContext(),
                JournalEntryDatabase.class, "journal-entry-database").build();

        // Set click listener for "View Entries" Button
        viewEntriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToJournalEntriesList();
            }
        });

        // Set click listener for "Save Entry" Button
        saveEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveJournalEntry();
            }
        });

        // Set click listener for Home Button
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TimesheetActivity.this, MainActivity.class));
                finish();
            }
        });

        // Set click listener for Back Button
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    // Set title
    private String getDailyChallenge() {
        return "Tee Time Booking System";
    }

    // Save the entered journal entry to the database
    private void saveJournalEntry() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Get entry text and current time
                String entryText = journalEntry.getText().toString();
                Date currentTime = new Date();

                // Create a new JournalEntry object
                JournalEntry entry = new JournalEntry();
                entry.entryText = entryText;
                entry.timestamp = currentTime;

                // Insert the entry into the database
                db.journalEntryDAO().insert(entry);

                // Update UI on the main thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        lastSavedTime.setText("Last saved on: " + dateFormat.format(currentTime));
                        journalEntry.setText("");
                    }
                });
            }
        }).start();
    }

    // Navigate to the JournalEntriesActivity
    private void navigateToJournalEntriesList() {
        Intent intent = new Intent(TimesheetActivity.this, JournalEntriesActivity.class);
        startActivity(intent);
    }
}
