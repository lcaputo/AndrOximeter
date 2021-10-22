package com.dev.androximeter;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class StartVitalSigns extends AppCompatActivity {
    private String user;
    private int p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 100);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_vital_signs);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("Usr");
            p = extras.getInt("Page");
        }

        Button VS = this.findViewById(R.id.StartVS);

        VS.setOnClickListener(v -> {
            //switch para ir a cada vista
            switch (p) {
                case 1: {
                    Intent i = new Intent(v.getContext(), HeartRateProcess.class);
                    startActivity(i);
                    finish();
                }
                break;

                case 2: {
                    Intent i = new Intent(v.getContext(), O2Process.class);
                    startActivity(i);
                    finish();
                }
                break;
            }
        });
    }



    @Override
    public void onBackPressed() {
        Intent i = new Intent(StartVitalSigns.this, MainActivity.class);
        startActivity(i);
        finish();
        super.onBackPressed();
    }


}
