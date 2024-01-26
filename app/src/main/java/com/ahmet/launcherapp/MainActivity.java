package com.ahmet.launcherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    @Override
    protected void onResume() {
        super.onResume();

        PackageManager packageManager = getPackageManager();
        Intent launchIntent = packageManager.getLaunchIntentForPackage("elektroland.acrob");

        if (launchIntent != null) {
            startActivity(launchIntent);
        } else {
            Toast.makeText(this, "Hatatatatat", Toast.LENGTH_SHORT).show();
        }
        startActivity(launchIntent);
    }
}