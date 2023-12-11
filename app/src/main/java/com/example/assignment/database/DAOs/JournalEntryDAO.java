package com.example.assignment.database.DAOs;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.assignment.database.entities.JournalEntry;
import java.util.List;

@Dao
public interface JournalEntryDAO {
    // Data Access Object (DAO) for JournalEntry entity

    // Insert a new JournalEntry into the database
    @Insert
    void insert(JournalEntry journalEntry);

    // Query to retrieve all JournalEntries from the database, ordered by timestamp in descending order
    @Query("SELECT * FROM journal_entries ORDER BY timestamp DESC")
    List<JournalEntry> getAllEntries();
}
