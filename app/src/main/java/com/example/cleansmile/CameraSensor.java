package com.example.cleansmile;

import android.Manifest;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.ImageCapture;
import androidx.fragment.app.FragmentActivity;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.Executor;

public class CameraSensor extends AppCompatActivity {
    private final Context context;
    private ActivityResultLauncher<String[]> activityResultLauncher;
    private ImageView imageView;

    public CameraSensor(Context context) {
        this.context = context;
        initActivityResultLauncher();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_sensor);


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

                    private void startCamera() {

                    }
                }
        );

    }
    private static final String[] REQUIRED_PERMISSIONS = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private void startCamera () {
        // Start the camera

    }

    //method to request permissions
    public void requestPermissions () {
        activityResultLauncher.launch(REQUIRED_PERMISSIONS);
    }

    public void doSomething () {
        SensorManager sem = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

    }

    public void takePicture (
            @NonNull ImageCapture.OutputFileOptions outputFileOptions,
            @NonNull Executor executor,
            @NonNull ImageCapture.OnImageSavedCallback imageSavedCallback
    ){
    }
}