package com.example.eecs4443project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button startVoiceBtn, startTouchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initalize();

    }

    private void initalize() {

        startVoiceBtn = (Button) findViewById(R.id.startVoice);
        startTouchBtn = (Button) findViewById(R.id.startTouch);

        startVoiceBtn.setOnClickListener(this);
        startTouchBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == startVoiceBtn) {
            Intent intent = new Intent(MainActivity.this, VoiceInputActivity.class);
            startActivity(intent);
        }
        else if (v == startTouchBtn) {
            Intent intent = new Intent(MainActivity.this, TouchInputActivity.class);
            startActivity(intent);
        }
    }
}