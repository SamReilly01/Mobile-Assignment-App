// JournalEntriesActivity.java

package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.assignment.database.JournalEntryDatabase;
import com.example.assignment.database.entities.JournalEntriesAdapter;
import com.example.assignment.database.entities.JournalEntry;

import java.util.List;

public class JournalEntriesActivity extends AppCompatActivity {

    // RecyclerView for displaying journal entries
    private RecyclerView recyclerView;

    // Adapter for populating the RecyclerView with journal entries
    private JournalEntriesAdapter adapter;

    // Database instance for accessing journal entries
    private JournalEntryDatabase db;

    // Buttons for navigation
    private Button buttonHome;
    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_entries);

        // Initialize views
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        buttonHome = findViewById(R.id.buttonHome);
        buttonBack = findViewById(R.id.buttonBack);

        // Initialize the Room Database
        db = Room.databaseBuilder(getApplicationContext(),
                JournalEntryDatabase.class, "journal-entry-database").build();

        // Load journal entries into the RecyclerView
        loadJournalEntries();

        // Set click listener for Home Button
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the main activity
                startActivity(new Intent(JournalEntriesActivity.this, MainActivity.class));
                finish();
            }
        });

        // Set click listener for Back Button
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the back button press
                onBackPressed();
            }
        });
    }

    // Load journal entries from the database and update the RecyclerView
    private void loadJournalEntries() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Retrieve journal entries from the database
                List<JournalEntry> entries = db.journalEntryDAO().getAllEntries();

                // Update the RecyclerView on the main (UI) thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Initialize the adapter and set it to the RecyclerView
                        adapter = new JournalEntriesAdapter(entries);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        }).start();
    }
}
