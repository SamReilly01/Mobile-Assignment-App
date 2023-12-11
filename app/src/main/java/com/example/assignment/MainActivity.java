// MainActivity.java

package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        // Set click listener for Button 1
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start TimesheetActivity when Button 1 is clicked
                Intent intent = new Intent(MainActivity.this, TimesheetActivity.class);
                startActivity(intent);
            }
        });

        // Set click listener for Button 2
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start ShopActivity when Button 2 is clicked
                Intent intent = new Intent(MainActivity.this, ShopActivity.class);
                startActivity(intent);
            }
        });

        // Set click listener for Button 3
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start OurTeamActivity when Button 3 is clicked
                Intent intent = new Intent(MainActivity.this, OurTeamActivity.class);
                startActivity(intent);
            }
        });
    }
}
