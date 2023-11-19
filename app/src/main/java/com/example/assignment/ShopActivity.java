package com.example.assignment;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        // Display Item 1
        displayItem(R.id.imageViewItem1, R.id.textViewItem1Name, R.id.textViewItem1Price,
                R.drawable.stealth_driver, "Taylormade Stealth Driver", "€349.99");

        // Display Item 2
        displayItem(R.id.imageViewItem2, R.id.textViewItem2Name, R.id.textViewItem2Price,
                R.drawable.spider_putter, "Taylormade Spider Putter", "€225.00");

        // Display Item 3
        displayItem(R.id.imageViewItem3, R.id.textViewItem3Name, R.id.textViewItem3Price,
                R.drawable.g425_hybrid, "Ping G425 Hybrid", "€179.99");

        // Display Item 4
        displayItem(R.id.imageViewItem4, R.id.textViewItem4Name, R.id.textViewItem4Price,
                R.drawable.p770_irons, "Taylormade P770 Iron Set", "€1137.99");

        // Display Item 5
        displayItem(R.id.imageViewItem5, R.id.textViewItem5Name, R.id.textViewItem5Price,
                R.drawable.vokey_wedges, "Titleist Vokey Wedges", "€649.99");

        // Display Item 6
        displayItem(R.id.imageViewItem6, R.id.textViewItem6Name, R.id.textViewItem6Price,
                R.drawable.scotty_putter, "Titleist Scotty Cameron Putter", "€300.00");
    }

    private void displayItem(int imageViewId, int nameTextViewId, int priceTextViewId,
                             int imageResource, String itemName, String itemPrice) {
        ImageView imageView = findViewById(imageViewId);
        TextView nameTextView = findViewById(nameTextViewId);
        TextView priceTextView = findViewById(priceTextViewId);

        imageView.setImageResource(imageResource);
        nameTextView.setText(itemName);
        priceTextView.setText(itemPrice);
    }
}
