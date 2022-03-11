package com.example.myfirstjob.ui;
// IGUAL QUE USER PERO SIN GUARDAR LA CONTRASEÑA Y SIENDO ESTÁTICO ES POSIBLE ACCEDER DESDE TODOS LOS SITIOS


import java.util.LinkedList;
import java.util.List;

public class UserView {
    public static UserView user;
    private String name;
    private String email;
    private List<Integer> listOffersId;
    private String dni;
    private String studies;
    private String companyName;

    public static void setUser(UserView user) {
        UserView.user = user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setStudies(String studies) {
        this.studies = studies;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public UserView(String name, String email, List<Integer> listOffersId, String dni, String studies, String companyName) {
        this.name = name;
        this.email = email;
        this.listOffersId = listOffersId;
        this.dni = dni;
        this.studies = studies;
        this.companyName = companyName;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Integer> getListOffersId() {
        return listOffersId;
    }

    public String getDni() {
        return dni;
    }

    public String getStudies() {
        return studies;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void addOffer(int offerId) {
        if (this.listOffersId == null)
            this.listOffersId = new LinkedList<>();
        this.listOffersId.add(offerId);
    }

}
