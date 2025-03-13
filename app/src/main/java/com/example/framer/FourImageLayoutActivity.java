package com.example.framer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.Collections;

public class FourImageLayoutActivity extends AppCompatActivity {
    private ImageView image1, image2, image3, image4;
    private Button toggleButton, chooseFrameButton;
    private ArrayList<String> imageUris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_image_layout);

        // Initialize Views
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        toggleButton = findViewById(R.id.toggleButton);
        chooseFrameButton = findViewById(R.id.chooseFrameButton);

        // Retrieve Images from Intent
        Intent intent = getIntent();
        imageUris = intent.getStringArrayListExtra("selectedImages");

        if (imageUris != null && imageUris.size() >= 4) {
            loadImages();
        }

        // Toggle Layout on Button Click
        toggleButton.setOnClickListener(v -> switchImages());

        // Navigate to Frame Selection Activity
        chooseFrameButton.setOnClickListener(v -> {
            Intent frameIntent = new Intent(FourImageLayoutActivity.this, FrameSelectionActivity.class);
            startActivity(frameIntent);
        });
    }

    private void loadImages() {
        Glide.with(this).load(Uri.parse(imageUris.get(0))).into(image1);
        Glide.with(this).load(Uri.parse(imageUris.get(1))).into(image2);
        Glide.with(this).load(Uri.parse(imageUris.get(2))).into(image3);
        Glide.with(this).load(Uri.parse(imageUris.get(3))).into(image4);
    }

    private void switchImages() {
        if (imageUris != null && imageUris.size() >= 4) {
            Collections.shuffle(imageUris); // Shuffle images randomly
            loadImages(); // Reload images in new order
        }
    }
}
