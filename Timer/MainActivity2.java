 package com.example.fittimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    Button stopwatch;
    Button timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        stopwatch = findViewById(R.id.stopwatchButton);
        stopwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStopwatchActivity();
            }




        });

        timer = findViewById(R.id.timerButton);
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimer();
            }
        });

    }

    public void openStopwatchActivity() {
        Intent intent = new Intent(this, StopwatchActivity.class);
        startActivity(intent);


    }

    public void openTimer(){
        Intent timerintent = new Intent(this, Timer.class);
        startActivity(timerintent);
    }

}