package com.example.myfirstjob.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstjob.R;
import com.example.myfirstjob.data.Login;
import com.google.android.material.snackbar.Snackbar;

public class LogInScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_screen);
    }

    /**
     * Método que es invocado por el botón de iniciar sesión
     *
     * @param view
     */
    public void goLoading(View view) {
        EditText edEmail = findViewById(R.id.etEmailAddress);
        EditText edPass = findViewById(R.id.etPassword);
        String email = edEmail.getText().toString();
        String password = edPass.getText().toString();
        if (login(email, password)) {
            successLogin(view);
            Intent i = new Intent(this, OfferViewerScreen.class);
            startActivity(i);
        } else {
            errorLogin(view);
        }
    }

    /**
     * Muestra un error por pantalla cuando el usuario o contraseña no es válido
     *
     * @param view
     */
    private void errorLogin(View view) {
        Snackbar.make(view, getString(R.string.error_login), Snackbar.LENGTH_LONG).show();
    }

    /**
     * Muestra una notificación por pantalla dando la bienvenida al usuario que ha iniciado sesión
     * correctamente
     *
     * @param view
     */
    private void successLogin(View view) {
        Snackbar.make(view, getString(R.string.welcome) + " " + UserView.user.getName(), Snackbar.LENGTH_LONG).show();
    }

    /**
     * Método que realiza la comprobación del inicio de sesión
     *
     * @param email
     * @param password
     * @return false en caso contrario
     */
    private boolean login(String email, String password) {
        Login login = new Login(this);
        return login.doOperation(email, password);
    }

    public void createUser(View view) {
        Intent i = new Intent(this, SignUpScreen.class);
        startActivity(i);
    }

    public void manual(View view) {
        Intent intent = new Intent(this, ManualActivity.class);
        startActivity(intent);
    }

}