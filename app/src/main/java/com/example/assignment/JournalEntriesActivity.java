package com.example.assignment;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_entries);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = Room.databaseBuilder(getApplicationContext(),
                JournalEntryDatabase.class, "journal-entry-database").build();

        loadJournalEntries();
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
