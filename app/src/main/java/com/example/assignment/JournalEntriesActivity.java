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

    private RecyclerView recyclerView;
    private JournalEntriesAdapter adapter;
    private JournalEntryDatabase db;

    private Button buttonHome;
    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_entries);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        buttonHome = findViewById(R.id.buttonHome);
        buttonBack = findViewById(R.id.buttonBack);

        db = Room.databaseBuilder(getApplicationContext(),
                JournalEntryDatabase.class, "journal-entry-database").build();

        loadJournalEntries();

        // Set click listener for Home Button
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JournalEntriesActivity.this, MainActivity.class));
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

    private void loadJournalEntries() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<JournalEntry> entries = db.journalEntryDAO().getAllEntries();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new JournalEntriesAdapter(entries);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        }).start();
    }
}
