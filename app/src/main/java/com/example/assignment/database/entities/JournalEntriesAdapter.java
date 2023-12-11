package com.example.assignment.database.entities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.R;

import java.util.List;

public class JournalEntriesAdapter extends RecyclerView.Adapter<JournalEntriesAdapter.ViewHolder> {
    // RecyclerView Adapter for displaying JournalEntry items

    private List<JournalEntry> journalEntries;

    // Constructor to initialize the adapter with a list of JournalEntries
    public JournalEntriesAdapter(List<JournalEntry> journalEntries) {
        this.journalEntries = journalEntries;
    }

    // Called when RecyclerView needs a new ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item in the RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_journal_entry_item, parent, false);
        return new ViewHolder(view);
    }

    // Called to display the data at a specific position
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the JournalEntry object at the specified position
        JournalEntry entry = journalEntries.get(position);

        // Set the entry text to the TextView in the ViewHolder
        holder.entryTextView.setText(entry.getEntryText());
    }

    // Returns the total number of items in the data set held by the adapter
    @Override
    public int getItemCount() {
        return journalEntries.size();
    }

    // ViewHolder class to hold references to the views for each item in the RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView entryTextView;

        // Constructor to initialize the ViewHolder with the item view
        public ViewHolder(View view) {
            super(view);
            entryTextView = view.findViewById(R.id.entryTextView);
        }
    }
}
