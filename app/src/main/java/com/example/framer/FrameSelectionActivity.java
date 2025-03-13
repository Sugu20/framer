package com.example.framer;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class FrameSelectionActivity extends AppCompatActivity {

    private ImageView framePreview;
    private Button applyBlackFrame, applyWoodFrame, applyGoldFrame, applyArtisticFrame, confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_selection);

        framePreview = findViewById(R.id.framePreview);
        applyBlackFrame = findViewById(R.id.blackFrame);
        applyWoodFrame = findViewById(R.id.woodFrame);
        applyGoldFrame = findViewById(R.id.goldFrame);
        applyArtisticFrame = findViewById(R.id.artisticFrame);
        confirmButton = findViewById(R.id.confirmButton);

        // Set click listeners to change frames
        applyBlackFrame.setOnClickListener(v -> setFrame(R.drawable.black_frame));
        applyWoodFrame.setOnClickListener(v -> setFrame(R.drawable.wood_frame));
        applyGoldFrame.setOnClickListener(v -> setFrame(R.drawable.gold_frame));
        applyArtisticFrame.setOnClickListener(v -> setFrame(R.drawable.artistic_frame));

        // Confirm Selection (You can proceed to saving or previewing)
        confirmButton.setOnClickListener(v -> {
            Intent intent = new Intent(FrameSelectionActivity.this, FinalPreviewActivity.class);
            startActivity(intent);
        });
    }

    private void setFrame(int drawableId) {
        Drawable frameDrawable = getResources().getDrawable(drawableId);
        framePreview.setBackground(frameDrawable);
    }
}
