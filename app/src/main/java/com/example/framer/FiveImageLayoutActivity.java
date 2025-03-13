package com.example.framer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;

public class FiveImageLayoutActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView selectedImageView;
    private ImageView image1, image2, image3, image4, image5;
    private Button toggleButton, newButton;
    private boolean isGridLayout1 = true; // Flag to toggle layout state

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five_image_layout);

        // Initialize ImageViews
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);

        toggleButton = findViewById(R.id.toggleButton);
        newButton = findViewById(R.id.newButton); // Added new button

        // Set click listeners for each image
        setImageClickListener(image1);
        setImageClickListener(image2);
        setImageClickListener(image3);
        setImageClickListener(image4);
        setImageClickListener(image5);

        // Toggle Button - Switch between layouts dynamically
        toggleButton.setOnClickListener(v -> {
            if (isGridLayout1) {
                toggleButton.setText("Switch to Layout 1");
                switchToAlternateLayout();
            } else {
                toggleButton.setText("Switch to Layout 2");
                switchToOriginalLayout();
            }
            isGridLayout1 = !isGridLayout1;
        });

        // New Button Click Listener
        newButton.setOnClickListener(v -> {
            // Add action for the new button (e.g., reset images, navigate, etc.)
            resetImages();
        });
    }

    // Method to set click listener for selecting an image
    private void setImageClickListener(ImageView imageView) {
        imageView.setOnClickListener(v -> {
            selectedImageView = (ImageView) v;
            openGallery();
        });
    }

    // Open gallery to pick an image
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    // Handle selected image from gallery
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                selectedImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Switch to alternative layout (example logic, update as needed)
    private void switchToAlternateLayout() {
        image3.getLayoutParams().height = 150;
        image3.getLayoutParams().width = getResources().getDisplayMetrics().widthPixels / 2;
        image3.requestLayout();
    }

    // Switch back to the original 2-1-2 layout
    private void switchToOriginalLayout() {
        image3.getLayoutParams().height = 200;
        image3.getLayoutParams().width = getResources().getDisplayMetrics().widthPixels;
        image3.requestLayout();
    }

    // Reset all images to default state
    private void resetImages() {
        image1.setImageResource(android.R.color.darker_gray);
        image2.setImageResource(android.R.color.darker_gray);
        image3.setImageResource(android.R.color.darker_gray);
        image4.setImageResource(android.R.color.darker_gray);
        image5.setImageResource(android.R.color.darker_gray);
    }
}
