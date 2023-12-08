package com.example.assignment.database.DAOs;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.assignment.database.entities.JournalEntry;
import java.util.List;

@Dao
public interface JournalEntryDAO {
    @Insert
    void insert(JournalEntry journalEntry);

    @Query("SELECT * FROM journal_entries ORDER BY timestamp DESC")
    List<JournalEntry> getAllEntries();
}
