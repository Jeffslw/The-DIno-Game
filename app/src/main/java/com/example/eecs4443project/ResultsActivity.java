package com.example.eecs4443project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Retrieve the timeSurvived value from the Intent
        long timeSurvived = getIntent().getLongExtra("timeSurvived", 0);
        int gameType = getIntent().getIntExtra("gameType", -1);

        // Format the timeSurvived value as a string
        String timeSurvivedString = String.format(getString(R.string.time_survived),
                timeSurvived / 3600,
                (timeSurvived % 3600) / 60,
                (timeSurvived % 60));

        // Display the time survived using a TextView
        TextView timeTextView = findViewById(R.id.result_time);
        timeTextView.setText(timeSurvivedString);

        // Handle the click event for the "Try Again" button
        Button tryAgainButton = findViewById(R.id.retry_button);
        tryAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gameType == GameLogic.TOUCH_INPUT) {
                    Intent intent = new Intent(ResultsActivity.this, TouchInputActivity.class);
                    startActivity(intent);
                } else if (gameType == GameLogic.VOICE_INPUT) {
                    Intent intent = new Intent(ResultsActivity.this, VoiceInputActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        });

        // Handle the click event for the "Main Menu" button
        Button mainMenuButton = findViewById(R.id.main_menu_button);
        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
