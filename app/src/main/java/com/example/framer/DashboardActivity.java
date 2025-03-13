package com.example.framer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    private ImageView profileImage;
    private Button btnUpload;
    private GridView imageGrid;
    private TextView instruction1, instruction2, instruction3, instruction4;

    private ArrayList<Uri> selectedImages = new ArrayList<>();
    private ImageAdapter imageAdapter;

    private final ActivityResultLauncher<String> imagePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.GetMultipleContents(),
            result -> {
                if (result.size() > 5) {
                    Toast.makeText(DashboardActivity.this, "You can select up to 5 images only", Toast.LENGTH_SHORT).show();
                } else {
                    selectedImages.clear();
                    selectedImages.addAll(result);
                    imageAdapter.notifyDataSetChanged();
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        profileImage = findViewById(R.id.profileImage);
        btnUpload = findViewById(R.id.btnUpload);
        imageGrid = findViewById(R.id.imageGrid);
        instruction1 = findViewById(R.id.instruction1);
        instruction2 = findViewById(R.id.instruction2);
        instruction3 = findViewById(R.id.instruction3);
        instruction4 = findViewById(R.id.instruction4);

        imageAdapter = new ImageAdapter(this, selectedImages);
        imageGrid.setAdapter(imageAdapter);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePickerLauncher.launch("image/*");
            }
        });
    }
}
