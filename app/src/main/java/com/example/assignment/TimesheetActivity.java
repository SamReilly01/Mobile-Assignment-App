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

    private EditText journalEntry;
    private Button saveEntryButton;
    private TextView challengeOfTheDay;
    private TextView lastSavedTime;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    private JournalEntryDatabase db;

    private Button viewEntriesButton;
    private Button buttonHome;
    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timesheet);

        journalEntry = findViewById(R.id.journalEntry);
        saveEntryButton = findViewById(R.id.saveEntryButton);
        challengeOfTheDay = findViewById(R.id.challengeOfTheDay);
        lastSavedTime = findViewById(R.id.lastSavedTime);
        viewEntriesButton = findViewById(R.id.viewEntriesButton);
        buttonHome = findViewById(R.id.buttonHome);
        buttonBack = findViewById(R.id.buttonBack);

        String todayChallenge = getDailyChallenge();
        challengeOfTheDay.setText(todayChallenge);

        db = Room.databaseBuilder(getApplicationContext(),
                JournalEntryDatabase.class, "journal-entry-database").build();

        viewEntriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToJournalEntriesList();
            }
        });

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

    private String getDailyChallenge() {
        return "Tee Time Booking System";
    }

    private void saveJournalEntry() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String entryText = journalEntry.getText().toString();
                Date currentTime = new Date();

                JournalEntry entry = new JournalEntry();
                entry.entryText = entryText;
                entry.timestamp = currentTime;

                db.journalEntryDAO().insert(entry);

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

    private void navigateToJournalEntriesList() {
        Intent intent = new Intent(TimesheetActivity.this, JournalEntriesActivity.class);
        startActivity(intent);
    }
}
