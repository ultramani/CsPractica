package com.example.myfirstjob.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.myfirstjob.R;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        final Switch swi = findViewById(R.id.switchNocturno);

        swi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(swi.isChecked()){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    getDelegate().applyDayNight();


                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    getDelegate().applyDayNight();
                }
            }
        });



    }

    /**
     * Carga la pantalla de Login
     */
    public void goLogin(View view) {
        Intent i = new Intent(this, LogInScreen.class);
        startActivity(i);
    }
}