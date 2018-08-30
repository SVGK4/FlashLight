package com.example.gopi.flashlight;

import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    CameraManager mCameraManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button on = findViewById(R.id.torch);
        Button off = findViewById(R.id.google);
        Button start = findViewById(R.id.start);
        Button stop = findViewById(R.id.stop);

        mCameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        final Intent serviceIntent = new Intent(MainActivity.this,AccelerometerService.class);
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                startService(serviceIntent);
            }
        });

        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        mCameraManager.setTorchMode("0", true);
                    }

                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        });

        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        mCameraManager.setTorchMode("0", false);
                    }

                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread.start();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(serviceIntent);
            }
        });

    }

}