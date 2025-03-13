import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FinalPreview extends AppCompatActivity {

    private ImageView finalFramePreview;
    private Button viewInArButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_preview);

        finalFramePreview = findViewById(R.id.finalFramePreview);
        viewInArButton = findViewById(R.id.viewInArButton);

        // Get selected frame and image from intent
        Intent intent = getIntent();
        if (intent != null) {
            String selectedFrame = intent.getStringExtra("selectedFrame");
            String selectedImageUri = intent.getStringExtra("selectedImage");

            if (selectedFrame != null) {
                // Set frame as background
                int frameResId = getResources().getIdentifier(selectedFrame, "drawable", getPackageName());
                finalFramePreview.setBackgroundResource(frameResId);
            }

            if (selectedImageUri != null) {
                // Set selected image
                Uri imageUri = Uri.parse(selectedImageUri);
                finalFramePreview.setImageURI(imageUri);
            }
        }

        // View in AR button click
        viewInArButton.setOnClickListener(v -> {
            // Launch AR Activity (Replace with your AR activity)
            Intent arIntent = new Intent(FinalPreview.this, ARActivity.class);
            startActivity(arIntent);
        });
    }
}
