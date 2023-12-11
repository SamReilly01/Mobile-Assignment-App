// OurTeamActivity.java

package com.example.assignment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class OurTeamActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 100;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ourteam);

        // Check and request location permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            // Permission already granted, proceed with location initialization
            initLocation();
        }

        // Team Member 1
        ImageView imageViewTeamMember1 = findViewById(R.id.imageViewTeamMember1);
        TextView textViewTeamMember1Role = findViewById(R.id.textViewTeamMember1Role);
        TextView textViewTeamMember1Description = findViewById(R.id.textViewTeamMember1Description);
        Button buttonBookLesson1 = findViewById(R.id.buttonBookLesson1);

        // Set details for Team Member 1
        imageViewTeamMember1.setImageResource(R.drawable.tiger_image);
        textViewTeamMember1Role.setText("Tiger Woods - Head Golf Professional");
        textViewTeamMember1Description.setText("Tiger, resident Head Golf Professional since October 2016, was awarded PGA Fellow status in 2013. Tiger has earned a fine reputation as a player, teacher, and retailer over the last two decades. His simple approach to coaching makes him very popular with golfers of all levels. Tiger won the PGA Irish region Order of Merit in 2001 and was a member of the 2000, 2003, and 2007 PGA Cup teams.");

        // Set click listener for Book a Lesson Button for Team Member 1
        buttonBookLesson1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implement the logic for booking a lesson for Team Member 1
                openLessonBookingActivity();
            }
        });

        // Team Member 2
        ImageView imageViewTeamMember2 = findViewById(R.id.imageViewTeamMember2);
        TextView textViewTeamMember2Role = findViewById(R.id.textViewTeamMember2Role);
        TextView textViewTeamMember2Description = findViewById(R.id.textViewTeamMember2Description);
        Button buttonBookLesson2 = findViewById(R.id.buttonBookLesson2);

        // Set details for Team Member 2
        imageViewTeamMember2.setImageResource(R.drawable.minwoo_image);
        textViewTeamMember2Role.setText("Min Woo Lee - Assistant Golf Professional");
        textViewTeamMember2Description.setText("Min Woo is a qualified PGA Professional having played full time on the PGA EuroPro Tour and Irish PGA Region. The experience and knowledge he has gained from his time on these tours are seen as an asset to assist in his coaching, custom fitting, and customer services.");

        // Set click listener for Book a Lesson Button for Team Member 2
        buttonBookLesson2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implement the logic for booking a lesson for Team Member 2
                openLessonBookingActivity();
            }
        });

        // Home Button
        Button buttonHome = findViewById(R.id.buttonHome);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OurTeamActivity.this, MainActivity.class));
                finish();
            }
        });

        // Back Button
        Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    // Open LessonBookingActivity
    private void openLessonBookingActivity() {
        Intent intent = new Intent(this, LessonBookingActivity.class);
        startActivity(intent);
    }

    // Initialize location services
    private void initLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Team Member 1
        updateLocationForTeamMember(R.id.textViewTeamMember1Location);

        // Team Member 2
        updateLocationForTeamMember(R.id.textViewTeamMember2Location);

    }

    // Update the location information for a team member
    private void updateLocationForTeamMember(int locationTextViewId) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    if (location != null) {
                        // Display the latitude and longitude in the specified TextView
                        TextView locationTextView = findViewById(locationTextViewId);
                        locationTextView.setText(String.format("Location: Latitude %s, Longitude %s", location.getLatitude(), location.getLongitude()));
                    }
                });
    }

    // Handle location permission request results
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with location initialization
                initLocation();
            } else {
                // Permission denied
                Toast.makeText(this, "Location permission denied. Some features may not work.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
