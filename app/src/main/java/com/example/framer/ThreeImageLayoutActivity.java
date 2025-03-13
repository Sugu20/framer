package com.example.framer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class ThreeImageLayoutActivity extends AppCompatActivity {

    private LinearLayout gridLayout, verticalLayout;
    private boolean isGridLayout = true; // Default layout is Grid
    private Button toggleButton, chooseFrameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_image_layout);

        // Initialize Views
        gridLayout = findViewById(R.id.gridLayout);
        verticalLayout = findViewById(R.id.verticalLayout);
        toggleButton = findViewById(R.id.toggleButton);
        chooseFrameButton = findViewById(R.id.chooseFrameButton);

        // Toggle Layout Button Click Listener
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isGridLayout) {
                    gridLayout.setVisibility(View.GONE);
                    verticalLayout.setVisibility(View.VISIBLE);
                } else {
                    gridLayout.setVisibility(View.VISIBLE);
                    verticalLayout.setVisibility(View.GONE);
                }
                isGridLayout = !isGridLayout; // Toggle state
            }
        });

        // Choose Frame Button Click Listener
        chooseFrameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Frame Selection Activity
                Intent intent = new Intent(ThreeImageLayoutActivity.this, FrameSelectionActivity.class);
                startActivity(intent);
            }
        });
    }
}
