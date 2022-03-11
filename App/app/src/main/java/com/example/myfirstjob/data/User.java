package com.example.myfirstjob.data;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private String name;
    private String email;
    private String password;
    private List<Integer> listOffersId;
    private String dni;
    private String studies;
    private String companyName;

    public User(String email, String password) {
        this.email = email;
        this.password = TypePassword.encryptPassword(password);
    }

    public User(String name, String email, String password, String dni) {
        this.name = name;
        this.email = email;
        this.password = TypePassword.encryptPassword(password);
        this.dni = dni;
    }

    public User(String name, String email, String password, List<Integer> listOffersId, String dni, String studies, String companyName) {
        this.name = name;
        this.email = email;
        this.password = TypePassword.encryptPassword(password);
        this.listOffersId = listOffersId;
        this.dni = dni;
        this.studies = studies;
        this.companyName = companyName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Integer> getListOffersId() {
        return listOffersId;
    }

    public void setListOffersId(List<Integer> listOffersId) {
        this.listOffersId = listOffersId;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getStudies() {
        return studies;
    }

    public void setStudies(String studies) {
        this.studies = studies;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public boolean testPassword(String password) {
        return this.password.equals(password);
    }

    /**
     * Función para cambiar la contraseña de un usuario
     *
     * @param old
     * @param pass
     * @param pass2
     * @return true si se ha cambiado correctamente
     */
    public boolean changePassword(String old, String pass, String pass2) {
        if (this.password.equals(old) && (pass.equals(pass2))) {
            this.password = pass;
        }
        return false;
    }

}
