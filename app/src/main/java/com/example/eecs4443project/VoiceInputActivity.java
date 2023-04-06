package com.example.eecs4443project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
public class VoiceInputActivity extends AppCompatActivity implements RecognitionListener {

    private final static int DELAY = 100;

    private SpeechRecognizer speechRecognizer;
    private Intent recognizerIntent;

    private ImageView land;

    private GameLogic gameLogic;
    private GameView gameView;
    private MainCharacter mainCharacter;
    private Obstacles obstacles;
    private boolean isJumping = false;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_input);

        gameView = findViewById(R.id.game_view);

        // Create GameLogic instance and set it on the GameView
        mainCharacter = gameView.getMainCharacter();
        obstacles = gameView.getObstacles();
        gameLogic = new GameLogic(mainCharacter, obstacles, gameView);
        gameView.setGameLogic(gameLogic);

        // Create the SpeechRecognizer and the RecognizerIntent
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        // Set voice listener on the gameView (??????) and start listening
        //gameView.set

        // Display land ImageView
        land = (ImageView) findViewById(R.id.landImage);

        // Set the floor image as the background of the floor ImageView
        land.setBackgroundResource(R.drawable.land);

        handler.post(gameLoopRunnable);
    }

    private Runnable gameLoopRunnable = new Runnable() {
        @Override
        public void run() {
            gameLogic.update();
            gameView.invalidate();
            handler.postDelayed(this, 20);
        }
    };

    @Override
    public void onReadyForSpeech(Bundle bundle) {}

    @Override
    public void onBeginningOfSpeech() {
        if (!isJumping) {
            isJumping = true;
            gameLogic.jump();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isJumping = false;
                }
            }, DELAY);
        }
    }

    @Override
    public void onRmsChanged(float v) {}

    @Override
    public void onBufferReceived(byte[] bytes) {}

    @Override
    public void onEndOfSpeech() {}

    @Override
    public void onError(int i) {}

    @Override
    public void onResults(Bundle bundle) {}

    @Override
    public void onPartialResults(Bundle bundle) {}

    @Override
    public void onEvent(int i, Bundle bundle) {}

    @Override
    protected void onStart() {
        super.onStart();
        speechRecognizer.setRecognitionListener(this);
        speechRecognizer.startListening(recognizerIntent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        speechRecognizer.stopListening();
        speechRecognizer.destroy();
    }
}
