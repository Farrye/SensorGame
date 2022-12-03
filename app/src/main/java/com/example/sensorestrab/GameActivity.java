package com.example.sensorestrab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity implements SensorEventListener{

    private SensorManager sensorManager;
    private Sensor acc;
    private Sensor gyr;


    private Chronometer chronometer;
    private boolean finishRun = false;
    private long pauseOffset;
    private TextView lastChronometerValue;
    private int numOfTries;


    private float x;
    private float y;
    private float z;
    //private float[] matrixDeRotacao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        chronometer = findViewById(R.id.Counting);

        Button button = findViewById(R.id.BackToMainActivity);
        button.setOnClickListener(view -> {
                finish();
            });

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        acc = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyr = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        //matrixDeRotacao = new float[9];
    }



    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            x = event.values[0];
            y = event.values[1];
            z = event.values[2];
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    @Override
    protected void onResume() {
        super.onResume();
        BeginChronometer();
        RestartChronometer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        FinishChronometer();
    }

    private void BeginChronometer(){
        if(!finishRun){
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            finishRun = true;
        }
    }

    private void FinishChronometer(){
        if(finishRun == true){
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
        }
    }

    private void RestartChronometer(){
        if(finishRun == true){
            finishRun = false;
        }
    }

    private void Collide(){

    }



}