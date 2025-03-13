package com.example.framer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    private ImageView profileImage;
    private Button btnUpload;
    private RecyclerView imageRecyclerView;
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
                    navigateToImageLayout(selectedImages.size());
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        profileImage = findViewById(R.id.profileImage);
        btnUpload = findViewById(R.id.btnUpload);
        imageRecyclerView = findViewById(R.id.imageRecyclerView);
        instruction1 = findViewById(R.id.instruction1);
        instruction2 = findViewById(R.id.instruction2);
        instruction3 = findViewById(R.id.instruction3);
        instruction4 = findViewById(R.id.instruction4);

        // Set up RecyclerView
        imageAdapter = new ImageAdapter(this, selectedImages);
        imageRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        imageRecyclerView.setAdapter(imageAdapter);

        btnUpload.setOnClickListener(v -> imagePickerLauncher.launch("image/*"));
    }

    private void navigateToImageLayout(int imageCount) {
        Class<?> targetActivity;
        switch (imageCount) {
            case 2:
                targetActivity = TwoImageLayoutActivity.class;
                break;
            case 3:
                targetActivity = ThreeImageLayoutActivity.class;
                break;
            case 4:
                targetActivity = FourImageLayoutActivity.class;
                break;
            case 5:
                targetActivity = FiveImageLayoutActivity.class;
                break;
            default:
                return;
        }

        Intent intent = new Intent(DashboardActivity.this, targetActivity);

        // Convert URI list to string array for passing through intent
        ArrayList<String> imageUris = new ArrayList<>();
        for (Uri uri : selectedImages) {
            imageUris.add(uri.toString());
        }

        intent.putStringArrayListExtra("selectedImages", imageUris);
        startActivity(intent);
    }
}

