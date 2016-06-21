package com.example.student.dodgetheblox;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by student on 2016-06-15.
 */
public class ReadFile {
    private String path;

    public ReadFile(String file_path){
        path = file_path;
    }

    public String[] OpenFile() throws IOException{
        FileReader fr = new FileReader(path);
        BufferedReader textReader = new BufferedReader(fr);

        int numberOfLines = ReadLines();
        String[] textData = new String[numberOfLines];

        for(int i = 0; i < numberOfLines; i++){
            textData[i] = textReader.readLine();
        }

        textReader.close();
        return textData;
    }

    int ReadLines() throws IOException{
        FileReader file_to_read = new FileReader(path);
        BufferedReader bf = new BufferedReader(file_to_read);

        int numberOfLines = 0;

        while(bf.readLine() != null){
            numberOfLines++;
        }
        bf.close();

        return numberOfLines;
    }
}
