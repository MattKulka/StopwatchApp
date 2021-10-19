package com.example.fittimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StopwatchActivity extends AppCompatActivity {

    Button startButton;
    Button pauseButton;
    Button backButton;
    Button lapButton;
    Button resetButton;

    TextView txtTimer;
    Handler customHandler = new Handler();
    LinearLayout container;

    long startTime = 0L, timeInMilliseconds = 0L, timeSwapBuff = 0L, updateTime = 0L;

    Runnable updateTimerThread = new Runnable(){
        @Override
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis()- startTime;
            updateTime = timeSwapBuff + timeInMilliseconds;
            int secs = (int)(updateTime/1000);
            int mins = secs/60;
            secs%=60;
            int miliseconds = (int)(updateTime%1000);
            txtTimer.setText(String.format("" + mins + ":" + String.format("%2d",secs)+":"
                                                           + String.format("%3d",miliseconds)));
            customHandler.postDelayed(updateTimerThread, 0);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        resetButton = findViewById(R.id.resetButton);
        backButton = findViewById(R.id.backButton);
        startButton = findViewById(R.id.startButton);
        pauseButton = findViewById(R.id.pauseButton);
        lapButton = findViewById(R.id.lapButton);
        txtTimer = findViewById(R.id.timerValue);
        container = (LinearLayout) findViewById(R.id.container);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime = SystemClock.uptimeMillis();

                customHandler.postDelayed(updateTimerThread, 0);
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeSwapBuff += timeInMilliseconds;
                customHandler.removeCallbacks(updateTimerThread);

            }
        });

        lapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View addView = inflater.inflate(R.layout.row, null);
                TextView txtValue = (TextView) addView.findViewById(R.id.txtContent);
                txtValue.setText(txtTimer.getText());
                container.addView(addView);

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity2();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtTimer.setText("00:00:00");


            }
        });


    }
    public void openMainActivity2(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);



    }



    }
