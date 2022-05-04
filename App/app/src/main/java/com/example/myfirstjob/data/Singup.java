package com.example.myfirstjob.data;

import android.content.Context;

import com.example.myfirstjob.persistence.Manage;
import com.example.myfirstjob.ui.UserView;

public class Singup {
    private Context context;

    public Singup(Context context) {
        this.context = context;
    }
    
    public Singup() {

    }
    
    /**
     * Operación por defecto de la clase Registrarse
     *
     * @return true si se ha registrado con éxito
     */
    public boolean doOperation(String name, String email, String dni, String password, String studies, String companyName) {
        // TODO: Recoger de las cajas del singup los datos del usuario
        User us = new User(name, email, password, null, dni, studies, companyName);
        UserView.user = new UserView(name, email, null, dni, studies, companyName);
        return saveUser(us);
    }

    /**
     * Guarda el usuario en los ficheros correspondientes
     *
     * @param us objeto de tipo User
     * @return true si todo ha ido correctamente
     */
    private boolean saveUser(User us) {
        // TODO: Guardar en el fichero el usuario
        Manage mg = new Manage();
        if (mg.addUser(context, us)) {
            UserView.user = new UserView(us.getName(), us.getEmail(), us.getListOffersId(), us.getDni(), us.getStudies(), us.getCompanyName());
        }
        return false;
    }
    
    public boolean comprobationPattern (String email) {
    Pattern pattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
    Matcher mat = pattern.matcher(email);
    boolean match= mat.matches();
    return match;
    }

}
