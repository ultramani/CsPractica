package com.example.myfirstjob.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstjob.R;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
    }

    /**
     * Carga la pantalla de Login
     */
    public void goLogin(View view) {
        Intent i = new Intent(this, LogInScreen.class);
        startActivity(i);
    }
}