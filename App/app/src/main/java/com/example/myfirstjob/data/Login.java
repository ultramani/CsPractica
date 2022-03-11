package com.example.myfirstjob.data;

import android.content.Context;

import com.example.myfirstjob.persistence.Manage;
import com.example.myfirstjob.ui.UserView;

public class Login {
    private Context context;

    public Login(Context context) {
        this.context = context;
    }

    /**
     * Clase que realiza la operación por defecto de la clase login que es iniciar sesión
     *
     * @param email
     * @param password
     * @return true si ha iniciado correctamente
     */
    public boolean doOperation(String email, String password) {
        User us = new User(email, password);
        return login(us, password);
    }

    /**
     * Comprueba el usuario y contraseña
     *
     * @param us
     * @param password
     * @return true si el usuario y contraseña es correcto
     */
    private boolean login(User us, String password) {
        Manage mg = new Manage();
        User user = mg.searchUser(us, context);
        if (user != null) {
            UserView.user = new UserView(user.getName(), user.getEmail(), user.getListOffersId(), user.getDni(), user.getStudies(), user.getCompanyName());
            return user.testPassword(TypePassword.encryptPassword(password));
        }
        return false;
    }
}



