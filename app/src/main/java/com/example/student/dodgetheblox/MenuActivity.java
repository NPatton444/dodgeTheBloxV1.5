package com.example.student.dodgetheblox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button playButton;
    Button leaderButton;
    Button controlsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Make Activity Full Screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_menu);

        playButton = (Button) findViewById(R.id.playButton);
        leaderButton = (Button) findViewById(R.id.leaderButton);
        controlsButton = (Button) findViewById(R.id.controlsButton);

        playButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                playButtonClick();
            }
        });

        controlsButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                controlsButtonClick();
            }
        });

        controlsButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                leaderButtonClick();
            }
        });
    }

    public void playButtonClick() {
        Intent menuIntent = new Intent(MenuActivity.this, GameActivity.class);
        MenuActivity.this.startActivity(menuIntent);
    }

    public void controlsButtonClick() {
    }

    public void leaderButtonClick() {
    }
}
