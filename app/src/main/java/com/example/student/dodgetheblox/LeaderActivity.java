package com.example.student.dodgetheblox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LeaderActivity extends AppCompatActivity {

    int READ_BLOCK_SIZE;
    static TextView leadersView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Make Activity Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_leader);

        READ_BLOCK_SIZE = 100;

        leadersView = (TextView) findViewById(R.id.leadersView);
    }

    public static void main(String[] args) throws IOException{
        String file_name = "C:\\Users\\student\\AndroidStudioProjects\\dodgeTheBloxV1.2\\app\\src\\main\\res\\highscores";

        try{
            ReadFile file = new ReadFile(file_name);
            String[] aryLines = file.OpenFile();

            for(int i = 0; i < aryLines.length; i++){
                leadersView.append(aryLines[i]);
            }
        }
        catch(IOException e){
            String damn = "Didn't Work.";
            leadersView.setText(damn);
        }
    }
}
