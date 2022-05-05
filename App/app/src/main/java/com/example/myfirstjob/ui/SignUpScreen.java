package com.example.myfirstjob.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstjob.R;
import com.example.myfirstjob.data.Singup;
import com.google.android.material.snackbar.Snackbar;

public class SignUpScreen extends AppCompatActivity {

    private Singup signUp = new Singup(getAppContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

    }

    public void storeUser(View view) {
    boolean correct = generateUser(view);

    if (correct) {
        Intent i = new Intent(this, OfferViewerScreen.class);
        startActivity(i);
    }
}

private boolean generateUser(View view) {
    EditText name = findViewById(R.id.Name);
    String Sname = name.getText().toString();
    name.setText(Sname);
    EditText email = findViewById(R.id.Email);
    String Semail = email.getText().toString();
    name.setText(Semail);
    EditText passwd = findViewById(R.id.Password);
    String Spasswd = passwd.getText().toString();
    name.setText(Spasswd);
    EditText dni = findViewById(R.id.DNI);
    String Sdni = dni.getText().toString();
    name.setText(Sdni);
    EditText studies = findViewById(R.id.Studies);
    String Sstudies = studies.getText().toString();
    name.setText(Sstudies);
    EditText comp = findViewById(R.id.Company);
    String Scomp = comp.getText().toString();
    name.setText(Scomp);

    if (signUp.comprobationPattern(Semail)) {
        if (signUp.dnicomprobationPattern(Sdni)) {
            signUp.doOperation(Sname, Semail, Sdni, Spasswd, Sstudies, Scomp);
            return true;
        }else{
            Snackbar snack = Snackbar.make(view, "Dni incorrecto", 10000);
            snack.show();
            return false;
        }
    } else {
        Snackbar snack = Snackbar.make(view, "Email incorrecto", 10000);
        snack.show();
        return false;
    }
}

    public Context getAppContext() {
        return this;
    }
}
