package com.example.myfirstjob.persistence;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class HelpFile {

    public static String read(Context context) {
        StringBuilder s = new StringBuilder("");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open("help.txt")));
            String line;
            while ((line = reader.readLine()) != null) {
                s.append(line + '\n');
            }
            reader.close();
        } catch (IOException e) {
        }
        return s.toString();
    }
}
