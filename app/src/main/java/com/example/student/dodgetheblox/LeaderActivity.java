package com.example.student.dodgetheblox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class LeaderActivity extends AppCompatActivity {

    int READ_BLOCK_SIZE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Make Activity Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_leader);

        READ_BLOCK_SIZE = 100;

        TextView leadersView = (TextView) findViewById(R.id.leadersView);

        try {
            FileInputStream fileIn = openFileInput("highscores.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";
            int charRead;

            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                // char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }
            InputRead.close();
            leadersView.setText(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
