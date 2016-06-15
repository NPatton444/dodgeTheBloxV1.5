package com.example.student.dodgetheblox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class LeaderActivity extends AppCompatActivity {

    HandleXML obj;
    XmlPullParserFactory xmlFactoryObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Make Activity Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_leader);

        try {
            xmlFactoryObject = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        XmlPullParser myparser = null;
        try {
            myparser = xmlFactoryObject.newPullParser();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        try {
            myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        myparser.setInput(R.xml.highscores2, null);

        obj = new HandleXML();
        TextView leaderView = (TextView) findViewById(R.id.leadersView);
        for(int i = 0; i < 10; i++){
            obj.parseXMLAndStoreIt(myparser);
            if(obj.getName() != null && obj.getScore() != null) {
                leaderView.append("\n" + obj.getName() + obj.getScore());
            }
        }
    }
}
