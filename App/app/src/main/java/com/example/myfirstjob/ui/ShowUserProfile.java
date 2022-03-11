package com.example.myfirstjob.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myfirstjob.R;
import com.example.myfirstjob.data.Offer;
import com.example.myfirstjob.data.User;
import com.example.myfirstjob.persistence.Manage;

public class ShowUserProfile extends AppCompatActivity {

    private int offerID;
    private String userEmail;
    private String offerName;
    private Offer offer;
    //private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_profile);
        Bundle bundle = getIntent().getExtras();
        offerID = (int) bundle.get("offerID");
        userEmail = (String) bundle.get("userEmail");
        offerName = (String) bundle.get("offerName");
        //user = new Manage().searchUserByID(userEmail, this);
        this.offer = getOffer();
        TextView txtOfferName = findViewById(R.id.txtOfferName);
        txtOfferName.setText("Oferta: " + offerName);
        showUserByID(userEmail);
        isUserAproved();
    }

    /**
     * Si el usuario ya ha sido aprobado con anterioridad que no se muestre el botón de aceptar
     * usuario
     */
    private void isUserAproved() {
        if (this.offer.isSuscribedAproved(this.userEmail)) {
            changeStatus(true);
        } else {
            changeStatus(false);
        }
    }

    private Offer getOffer() {
        Manage manage = new Manage();
        return manage.getOfferByID(this.offerID, this);
    }

    public void acceptUser(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Aceptar candidato");
        dialog.setMessage("¿Seguro que desea aceptar a " + this.userEmail + "?");
        dialog.setCancelable(false);
        dialog.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                acceptOrRejectUser(true);
                changeStatus(true);
            }
        });
        dialog.setNegativeButton("NO", null);
        dialog.show();
    }

    private void changeStatus(boolean bool) {
        ConstraintLayout layUserAceppted = findViewById(R.id.layUserAccepted);
        Button btnUserAccept = findViewById(R.id.btnUserAccept);
        if (bool) {
            layUserAceppted.setVisibility(View.VISIBLE);
            btnUserAccept.setVisibility(View.INVISIBLE);
        } else {
            layUserAceppted.setVisibility(View.INVISIBLE);
            btnUserAccept.setVisibility(View.VISIBLE);
        }
    }

    public void rejectUser(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Rechazar candidato");
        dialog.setMessage("¿Seguro que desea rechazar a " + this.userEmail + "?");
        dialog.setCancelable(false);
        dialog.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                acceptOrRejectUser(false);
                changeStatus(false);
            }
        });
        dialog.setNegativeButton("NO", null);
        dialog.show();
    }

    private void acceptOrRejectUser(boolean b) {
        Manage manage = new Manage();
        if (this.offer != null) {
            if (b) {
                // USUARIO APROBADO
                this.offer.addInscrp_aproved(this.userEmail);
            } else {
                offer.removeUser(this.userEmail);
            }
            manage.updateOffers(this, offer);
        } else {
            // Ha ocurrido algún error
        }
    }

    private void showUserByID(String userEmail) {
        User user = new Manage().searchUserByID(userEmail, this);
        // MOSTRAR EN PANTALLA EL USUARIO

        TextView txtName = findViewById(R.id.txtName);
        txtName.setText(txtName.getText() + user.getName());

        TextView txtEmail = findViewById(R.id.txtEmail);
        txtEmail.setText(txtEmail.getText() + user.getEmail());

        TextView txtStudies = findViewById(R.id.txtStudies);
        txtStudies.setText(txtStudies.getText() + user.getStudies());

        TextView txtCompany = findViewById(R.id.txtCompany);
        String company = user.getCompanyName();
        if (user.getCompanyName().equals("")) {
            company = "No especificado";
        }
        txtCompany.setText(txtCompany.getText() + company);
    }

    public void close(View v) {
        super.onBackPressed();
    }
}