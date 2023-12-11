package com.example.assignment.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(tableName = "journal_entries")
public class JournalEntry {
    // Room Database entity representing a Journal Entry

    // Primary key for the entity with auto-generation
    @PrimaryKey(autoGenerate = true)
    public int id;

    // Fields representing the attributes of a Journal Entry
    public String entryText;
    public Date entryDate;
    public Date timestamp;

    // Default constructor required by Room
    public JournalEntry() {
    }

    // Getter and setter methods for each attribute

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEntryText() {
        return entryText;
    }

    public void setEntryText(String entryText) {
        this.entryText = entryText;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
