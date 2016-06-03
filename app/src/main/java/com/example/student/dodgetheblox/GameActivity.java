package com.example.student.dodgetheblox;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;


public class GameActivity extends AppCompatActivity {

    int cycles;
    ArrayList<Blox> bloxList;
    int width, height, playerX, playerY, playerSpeed, playerDirection, playerWidth, playerHeight;
    Player p;
    Drawable playerImage;
    Resources res;
    //Get screen dimensions
    Display display;
    OnDraw v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Make Activity Full Screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_game);

        onStartUp();
    }
    public void onStartUp(){
        cycles = 0;

        display = getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();

        //Access Player Image
        res = getResources();
        playerImage = res.getDrawable(R.drawable.playership);

        //Set initial value of Player
        playerX = width / 2 - playerWidth / 2;
        playerY = height - playerHeight - 20;
        playerDirection = 0;
        playerSpeed = 0;
        playerWidth = playerImage.getIntrinsicWidth();
        playerHeight = playerImage.getIntrinsicHeight();
        p = new Player(playerX, playerY, playerSpeed, playerDirection, playerWidth, playerHeight);

        //Initial Block position
        bloxList = new ArrayList<>();
        Blox firstBlock = new Blox(width / 2, - 10, 5, 5);
        bloxList.add(firstBlock);

        execute();
    }
    public void execute(){
       new CountDownTimer(17, 1) {
            @Override
            public void onTick(long millisUntilFinished) {}

            @Override
            public void onFinish() {
                onExecute();
            }
        }.start();
    }
    public void onExecute(){
        cycles += 1;

        if (cycles == 10) {
            Random randGen = new Random();
            int blockX = randGen.nextInt(width) + 10;
            int blockY = -7;
            int blockSpeed = 5;
            int blockSize = 5;
            Blox b = new Blox(blockX, blockY, blockSpeed, blockSize);
            bloxList.add(b);
            cycles = 0;
        }

        for(Blox b : bloxList) {
            b.Move(b.y, b.speed);
        }
        onDraw();
    }
    public void onDraw(){
//        v = new OnDraw(this);
//        setContentView(v);
    }
}

