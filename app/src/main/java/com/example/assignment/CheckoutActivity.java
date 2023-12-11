// CheckoutActivity.java

package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CheckoutActivity extends AppCompatActivity {

    private EditText cardNumberInput;
    private EditText cardExpiryInput;
    private EditText cvcInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Initialize views
        cardNumberInput = findViewById(R.id.cardNumberInput);
        cardExpiryInput = findViewById(R.id.cardExpiryInput);
        cvcInput = findViewById(R.id.cvcInput);

        // Display a message indicating that the checkout page is under construction
        TextView checkoutTitle = findViewById(R.id.checkoutTitle);
        checkoutTitle.setText("Checkout");

        // Set up the "Pay" button click listener
        Button payButton = findViewById(R.id.payButton);
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve values from EditText fields
                String cardNumber = cardNumberInput.getText().toString();
                String cardExpiry = cardExpiryInput.getText().toString();
                String cvc = cvcInput.getText().toString();

                // Placeholder for payment logic
                simulatePayment(cardNumber, cardExpiry, cvc);
            }
        });

        // Retrieve references to Home and Back buttons
        Button buttonHome = findViewById(R.id.buttonHome);
        Button buttonBack = findViewById(R.id.buttonBack);

        // Set click listener for Home Button
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CheckoutActivity.this, MainActivity.class));
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

    // Placeholder for payment logic
    private void simulatePayment(String cardNumber, String cardExpiry, String cvc) {
        // Here, you would typically integrate with a payment gateway

        // For now, let's display a toast message indicating successful payment
        Toast.makeText(this, "Payment successful!", Toast.LENGTH_SHORT).show();

        // Set the thank you message
        TextView thankYouMessage = findViewById(R.id.thankYouMessage);
        thankYouMessage.setText("Thank you for your purchase!");
    }
}
