package com.example.cleansmile.ui.main;

import android.content.Context;
import android.hardware.SensorManager;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.impl.utils.ContextUtil;

import com.example.cleansmile.RealMainActivity;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.Executor;

public class SensorCamera {
    private ActivityResultLauncher<String[]> activityResultLauncher(
                    new ActivityResultContracts.RequestMultiplePermissions(),
                    new ActivityResultCallback<Map<String, Boolean>>() {
                        @Override
                        public void onActivityResult(Map<String, Boolean> permissions) {
                            // Handle Permission granted/rejected
                            boolean permissionGranted = true;
                            for (Map.Entry<String, Boolean> entry : permissions.entrySet()) {
                                if (Arrays.asList(REQUIRED_PERMISSIONS).contains(entry.getKey()) && !entry.getValue()) {
                                    permissionGranted = false;
                                }
                            }
                            if (!permissionGranted) {
                                Toast.makeText(ContextUtil.getApplicationContext(RealMainActivity),
                                        "Permission request denied",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                startCamera();
                            }
                        }
                    }
            );




    // Define REQUIRED_PERMISSIONS as a String array
    private static final String[] REQUIRED_PERMISSIONS = new String[] {
            // Add the required permissions here
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private void startCamera() {
        // Start the camera
    }

    // Call this method to request permissions
    private void requestPermissions() {
        activityResultLauncher.launch(REQUIRED_PERMISSIONS);
    }
    public void dosomething(Context context){
        String stdSensorServiceName = context.SENSOR_SERVICE;
        SensorManager sem;
        sem = (SensorManager) context.getSystemService(stdSensorServiceName);


    }
    public void takePicture(
            @NonNull ImageCapture.OutputFileOptions outputFileOptions,
            @NonNull Executor executor,
            @NonNull ImageCapture.OnImageSavedCallback imageSavedCallback
    ){

    }
}
