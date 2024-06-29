package com.example.cleansmile;

import android.Manifest;
import android.content.Context;
import android.hardware.SensorManager;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.camera.core.ImageCapture;
import androidx.fragment.app.FragmentActivity;


import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.Executor;

public class SensorCamera {

    private final Context context;
    private ActivityResultLauncher<String[]> activityResultLauncher;

    public SensorCamera(Context context) {
        this.context = context;
        initActivityResultLauncher();
    }

    private void initActivityResultLauncher() {
        activityResultLauncher = ((FragmentActivity) context).registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(),
                new ActivityResultCallback<Map<String, Boolean>>() {
                    @Override
                    public void onActivityResult(Map<String, Boolean> permissions) {
                        boolean permissionGranted = true;
                        for (Map.Entry<String, Boolean> entry : permissions.entrySet()) {
                            if (Arrays.asList(REQUIRED_PERMISSIONS).contains(entry.getKey()) && !entry.getValue()) {
                                permissionGranted = false;
                                break;
                            }
                        }
                        if (!permissionGranted) {
                            Toast.makeText(context, "Permission request denied", Toast.LENGTH_SHORT).show();
                        } else {
                            startCamera();
                        }
                    }
                }
        );
    }

    // Define REQUIRED_PERMISSIONS as a String array
    private static final String[] REQUIRED_PERMISSIONS = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private void startCamera() {
        // Start the camera
    }

    // Call this method to request permissions
    public void requestPermissions() {
        activityResultLauncher.launch(REQUIRED_PERMISSIONS);
    }

    public void doSomething() {
        SensorManager sem = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        // Do something with the sensor manager
    }

    public void takePicture(
            @NonNull ImageCapture.OutputFileOptions outputFileOptions,
            @NonNull Executor executor,
            @NonNull ImageCapture.OnImageSavedCallback imageSavedCallback
    ) {
        // Implement take picture functionality
    }
}