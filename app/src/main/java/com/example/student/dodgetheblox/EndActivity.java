package com.example.student.dodgetheblox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class EndActivity extends AppCompatActivity {

    Button enterButton;
    EditText initialsInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Make Activity Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_end);

        initialsInput = (EditText) findViewById(R.id.editText);

        enterButton = (Button) findViewById(R.id.enterButton);

        enterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterButtonClick();
            }
        });
    }

    public void enterButtonClick() {
        if (initialsInput.getText().length() != 3) {
            String errorMessage = "Please enter 3 letters";
            initialsInput.setText(errorMessage);
        } else {
            int i = 0;
            for (HighScores hs : MainActivity.highScoresList) {
                if (GameActivity.score > hs.score) {
                    String name = initialsInput.getText().toString();
                    HighScores newHighScore = new HighScores(GameActivity.score, name);
                    MainActivity.highScoresList.add(i, newHighScore);
                    break;
                }else {
                    i++;
                }
            }

            if (MainActivity.highScoresList.isEmpty() || GameActivity.score <= 160) {
                String name = initialsInput.getText().toString();
                HighScores newHighScore = new HighScores(GameActivity.score, name);
                MainActivity.highScoresList.add(newHighScore);
            }

            Intent gameIntent = new Intent(EndActivity.this, MenuActivity.class);
            EndActivity.this.startActivity(gameIntent);
        }
    }
}
