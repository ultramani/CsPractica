package com.example.myfirstjob.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstjob.R;
import com.example.myfirstjob.persistence.HelpFile;

public class ManualActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);
        TextView text = findViewById(R.id.textDisplay);
        text.setText(HelpFile.read(this));
    }


}