package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class OurTeamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ourteam);

        // Team Member 1
        ImageView imageViewTeamMember1 = findViewById(R.id.imageViewTeamMember1);
        TextView textViewTeamMember1Role = findViewById(R.id.textViewTeamMember1Role);
        TextView textViewTeamMember1Description = findViewById(R.id.textViewTeamMember1Description);

        // Replace the placeholder content with actual data
        imageViewTeamMember1.setImageResource(R.drawable.tiger_image); // Replace with the actual image resource
        textViewTeamMember1Role.setText("Tiger Woods - Head Golf Professional"); // Replace with the actual role
        textViewTeamMember1Description.setText("Tiger, resident Head Golf Professional since October 2016, was awarded PGA Fellow status in 2013.  Tiger has earned a fine reputation as a player, teacher and retailer over the last two decades.  His simple approach to coaching makes him very popular with golfers of all levels. Tiger won the PGA Irish region Order of Merit in 2001 and was a member of the 2000, 2003 and 2007 PGA Cup teams.");

        // Team Member 2
        ImageView imageViewTeamMember2 = findViewById(R.id.imageViewTeamMember2);
        TextView textViewTeamMember2Role = findViewById(R.id.textViewTeamMember2Role);
        TextView textViewTeamMember2Description = findViewById(R.id.textViewTeamMember2Description);

        // Replace the placeholder content with actual data
        imageViewTeamMember2.setImageResource(R.drawable.minwoo_image); // Replace with the actual image resource
        textViewTeamMember2Role.setText("Min Woo Lee - Assistant Golf Professional"); // Replace with the actual role
        textViewTeamMember2Description.setText("Min Woo is qualified PGA Professional having played full time on the PGA EuroPro Tour and Irish PGA Region. The experience and knowledge he has gained from his time on these tours, are seen as asset to assist in his coaching, custom fitting and customer services. Min Woo’s approach to coaching is teaching by means of guided understanding in achieving students goals and ambitions");

        // Add more code for additional team members as needed
    }
}
