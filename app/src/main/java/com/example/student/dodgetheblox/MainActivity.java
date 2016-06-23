package com.example.student.dodgetheblox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<HighScores> highScoresList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Make Activity Full Screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        highScoresList = new ArrayList<>();

        setContentView(R.layout.activity_main);
        Intent gameIntent = new Intent(MainActivity.this, MenuActivity.class);
        MainActivity.this.startActivity(gameIntent);
    }
}
