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

public class TwoImageLayoutActivity extends AppCompatActivity {
    private ImageView imageTop, imageBottom, imageLeft, imageRight;
    private View topBottomBox, leftRightBox;
    private Button toggleButton, chooseFrameButton;
    private boolean isTopBottomLayout = true; // Default layout
    private ArrayList<String> imageUris; // Store selected images

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_image_layout);

        // Initialize views
        imageTop = findViewById(R.id.imageTop);
        imageBottom = findViewById(R.id.imageBottom);
        imageLeft = findViewById(R.id.imageLeft);
        imageRight = findViewById(R.id.imageRight);
        toggleButton = findViewById(R.id.toggleButton);
        chooseFrameButton = findViewById(R.id.chooseFrameButton); // New button
        topBottomBox = findViewById(R.id.topBottomBox);
        leftRightBox = findViewById(R.id.leftRightBox);

        // Retrieve images from Intent
        Intent intent = getIntent();
        imageUris = intent.getStringArrayListExtra("selectedImages");

        if (imageUris != null && imageUris.size() >= 2) {
            Glide.with(this).load(Uri.parse(imageUris.get(0))).into(imageTop);
            Glide.with(this).load(Uri.parse(imageUris.get(1))).into(imageBottom);
            Glide.with(this).load(Uri.parse(imageUris.get(0))).into(imageLeft);
            Glide.with(this).load(Uri.parse(imageUris.get(1))).into(imageRight);
        }

        // Toggle layout when button is clicked
        toggleButton.setOnClickListener(v -> toggleLayout());

        // Open Frame Selection when button is clicked
        chooseFrameButton.setOnClickListener(v -> openFrameSelection());
    }

    private void toggleLayout() {
        if (isTopBottomLayout) {
            topBottomBox.setVisibility(View.GONE);
            leftRightBox.setVisibility(View.VISIBLE);
        } else {
            topBottomBox.setVisibility(View.VISIBLE);
            leftRightBox.setVisibility(View.GONE);
        }
        isTopBottomLayout = !isTopBottomLayout;
    }

    private void openFrameSelection() {
        Intent intent = new Intent(TwoImageLayoutActivity.this, FrameSelectionActivity.class);
        intent.putStringArrayListExtra("selectedImages", imageUris);
        startActivity(intent);
    }
}
