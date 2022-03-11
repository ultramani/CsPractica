package com.example.myfirstjob;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstjob.ui.WelcomeScreen;


public class MainActivity extends AppCompatActivity {
    public static int lastOfferID = 0;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, WelcomeScreen.class);
        startActivity(intent);
        finish();
    }

    public static Context getAppContext() {
        return MainActivity.context;
    }

    /**
     * Método que se ejecuta cuando el foco vuelve a la aplicación
     * Lo que hace es cargar en memoria el último id utilizado en las ofertas
     */
    protected void onResume() {
        super.onResume();
        cargarOfferID();
    }

    /**
     * Método que se ejecuta cuando se pierde el foco de la aplicación
     * Se encarga de guardar el último id utilzado en las ofertas para continuar la próxima vez
     */
    protected void onPause() {
        super.onPause();
        guardarOfferID();
    }

    /**
     * Método que carga el ID de oferta en la variable lastOfferID
     */
    private void cargarOfferID() {
        SharedPreferences sharedPref = getSharedPreferences("offerID", Context.MODE_PRIVATE);
        lastOfferID = sharedPref.getInt("offerID", 0);
    }

    /**
     * Método que guarda el ID de oferta
     */
    private void guardarOfferID() {
        SharedPreferences sharedPref = getSharedPreferences("offerID", Context.MODE_PRIVATE);
        Editor editor = sharedPref.edit();
        editor.putInt("offerID", lastOfferID);
        editor.commit();
    }
}