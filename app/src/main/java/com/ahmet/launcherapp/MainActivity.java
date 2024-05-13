package com.ahmet.launcherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ahmet.launcherapp.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    public static final String DEFAULT_APP_NAME = "elektroland.acrob";
    public static final String APP_NAME_PREF_KEY = "APP_NAME";
    private String appName;


    TinyDB tinyDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tinyDB = new TinyDB(this);

        appName = tinyDB.getString(APP_NAME_PREF_KEY , DEFAULT_APP_NAME);
        binding.startupAppNameEditText.setText(appName);
        binding.startupAppNameSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    tinyDB.putString(APP_NAME_PREF_KEY, binding.startupAppNameEditText.getText().toString());
                }
                catch (Exception e){
                    binding.infoTextView.setText(e.getMessage());

                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();


        // Intent oluşturun
        Intent intent = getPackageManager().getLaunchIntentForPackage(appName);

        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
        } else {
            binding.infoTextView.setText("Startup app not found " + appName);
        }

    }

    public static AdbCommandResult runAdbCommand(String command) {
        StringBuilder outputBuilder = new StringBuilder();
        StringBuilder errorBuilder = new StringBuilder();
        int exitCode = -1;

        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                outputBuilder.append(line).append("\n");
            }

            String errorLine;
            while ((errorLine = errorReader.readLine()) != null) {
                errorBuilder.append(errorLine).append("\n");
            }

            exitCode = process.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return new AdbCommandResult(outputBuilder.toString(), errorBuilder.toString(), exitCode);
    }
}