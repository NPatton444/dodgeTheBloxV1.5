package com.example.student.dodgetheblox;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class GameActivity extends Activity implements SensorEventListener {

    //region Vars
    int cycles, cycleCycles, cyclesLoop , scoreCycles, scoreAdder;
    public static int score;

    //List of block objects
    public static ArrayList<Blox> bloxList;

    //integers for player
    int width, height, playerDirection, playerWidth, playerHeight;
    float playerX, playerY, pAccel, pVel, playerSpeed;

    //Booleans for player direction
    boolean right, left;

    //Integers for blox
    int bloxWidth, bloxHeight;

    //Player object
    public static Player p;

    //Drawable images
    Drawable playerImage;
    Drawable bloxImage;
    Resources res;

    //Get view object;
    OnDraw v;

    //Display
    Display display;

    //Create Sensor
    private SensorManager sensorManager;

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Create Sensor Manager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //Make Activity Full Screen and portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        v = new OnDraw(this);
        setContentView(v);

        cycles = cycleCycles = score = 0;
        cyclesLoop = 15;
        scoreAdder = 10;

        display = getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();

        //Access Player Image
        res = getResources();
        playerImage = res.getDrawable(R.drawable.playership);

        //Set initial value of Player
        playerWidth = playerImage.getIntrinsicWidth();
        playerHeight = playerImage.getIntrinsicHeight();
        playerX = width / 2 - playerWidth / 2;
        playerY = height - playerHeight - 20;
        playerDirection = 0;
        playerSpeed = 0;
        p = new Player(playerX, playerY, playerSpeed, playerWidth, playerHeight);

        //Initial Block position
        bloxList = new ArrayList<>();
        bloxImage = res.getDrawable(R.drawable.yellowblock);
        bloxWidth = bloxImage.getIntrinsicWidth();
        bloxHeight = bloxImage.getIntrinsicHeight();
        Blox firstBlock = new Blox(width / 2 - bloxWidth / 2, -10, bloxWidth, bloxHeight, 5);
        bloxList.add(firstBlock);
        execute();
    }

    public void execute() {
        new CountDownTimer(17, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                onExecute();
            }
        }.start();
    }

    //region Sensor
    @Override
    public void onStart() {
        super.onStart();

        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_FASTEST);

    }

    @Override
    public void onStop() {
        sensorManager.unregisterListener(this);
        super.onStop();

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            pAccel = event.values[0];
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    //endregion

    public void onExecute() {

        //region Blox Update

        cycles++;

        if (cycles == cyclesLoop) {
            Random randGen = new Random();
            int bloxX = randGen.nextInt(width - bloxWidth) + 10;
            int bloxY = -10;
            int bloxSpeed = 5;
            Blox b = new Blox(bloxX, bloxY, bloxWidth, bloxHeight, bloxSpeed);
            bloxList.add(b);
            cycles = 0;

            //Add to score
            score += scoreAdder;

            //Speed up blox falling every 100 cycles
            cycleCycles++;
            if(cycleCycles == 100 && cyclesLoop > 0){
                cyclesLoop--;
                cycleCycles = 0;
            }

            //Add x10 scoreAdder every 10 cycles
            scoreCycles++;
            if(scoreCycles == 100){
                scoreAdder += 100;
                scoreCycles = 0;
            }
        }

        //Move Blox down screen
        for (Blox b : bloxList) {
            b.Move(b);
        }

        //Remove Blox from list after screen height is reached
        for (Blox b : bloxList) {
            if (b.y > height + 5) {
                bloxList.remove(b);
            }
            break;
        }

        //endregion

        //region Player Update

        //Calculations for moving player
        float frameTime = 0.666f;
        pVel += (pAccel * frameTime);
        p.speed = (pVel / 2) * frameTime;

        //Check for speed positive or negative
        if(p.speed > 0){
            right = true;
            left = false;
        }
        if(p.speed < 0){
            left = true;
            right = false;
        }

        //Check if speed reset is required (checks direction change)
        if(right && pAccel < 0){
            p.speed = 0;
            pVel = 0;
            pAccel = 0;
            right = false;
        }else if(left && pAccel > 0){
            p.speed = 0;
            pVel = 0;
            pAccel = 0;
            left = false;
        }else if(pAccel == 0){
            p.speed = 0;
        }

        //Check if player is off screen then reset to other side if necessary
        if(p.x < -(playerWidth + 10)){
            p.x = width - 5;
        }else if(p.x > width + 10){
            p.x = -(playerWidth - 5);
        }

        //Move player
        if (p.x > -(playerWidth + 15) && p.x < width + 15) {
            p.Move(p);
        }

        //endregion

        //Run timer over
        execute();
    }
}

