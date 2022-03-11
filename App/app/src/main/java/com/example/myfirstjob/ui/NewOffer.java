package com.example.myfirstjob.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myfirstjob.R;
import com.example.myfirstjob.data.JobOffer;
import com.example.myfirstjob.data.Offer;
import com.example.myfirstjob.data.Practice;
import com.example.myfirstjob.persistence.Manage;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class NewOffer extends AppCompatActivity {
    private EditText etName;
    private EditText etCompany;
    private Spinner spTypeOfWork;
    private EditText etSalary;
    private SwitchCompat swActive;
    private EditText etHours;
    private EditText etDescription;
    private Spinner spTypeOfContract;
    private SwitchCompat swTypeOffer;
    private Button btnSave;
    private Button btnClose;
    private Button btnEdit;
    private Button btnEditSave;
    private Button btnInscrib;
    private List<Offer> lOffers;
    private int position;
    private int offerID;
    private Button btnRemove;
    private ConstraintLayout layContent;
    private ConstraintLayout layModal;
    private FloatingActionButton helpButton;
    private TextView txtTitulo;
    private FloatingActionButton users;
    private Offer o;

    /**
     * Recoge todos los campos del activity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_offer);
        Bundle bundle = getIntent().getExtras();
        etName = findViewById(R.id.etName);
        etCompany = findViewById(R.id.etCompany);
        spTypeOfWork = findViewById(R.id.spTypeWork);
        etSalary = findViewById(R.id.etSalary);
        swActive = findViewById(R.id.swOfferBool);
        etHours = findViewById(R.id.etHours);
        spTypeOfContract = findViewById(R.id.spTypeOfContract);
        etDescription = findViewById(R.id.etDescription);
        swTypeOffer = findViewById(R.id.swTypeOffer);
        btnSave = findViewById(R.id.btnSaveNewOffer);
        btnClose = findViewById(R.id.btnClose);
        btnEditSave = findViewById(R.id.btnEditSave);
        btnInscrib = findViewById(R.id.btnInscrib);
        btnEdit = findViewById(R.id.btnEdit);
        btnRemove = findViewById(R.id.btnRemove);
        helpButton = findViewById(R.id.helpButton);
        layContent = findViewById(R.id.layContent);
        layModal = findViewById(R.id.layModal);
        txtTitulo = findViewById(R.id.textView2);
        users = findViewById(R.id.users);
        /*
         * Comprueba si se ha especificado si los campos van a ser editables o no y así los pone
         */
        if ((Boolean) bundle.get("editable")) {
            // NUEVA OFERTA
            setStatusLayout(true);
            txtTitulo.setText(R.string.new_offer);
        } else {
            // VER OFERTA
            lOffers = (List<Offer>) bundle.get("lOffers");
            position = (int) bundle.get("int_offer");


            o = lOffers.get(position);
            this.offerID = o.getOfferId();
            setStatusLayout(false);
            txtTitulo.setText(R.string.show_offer);
            showOffer(o);
        }
    }

    /**
     * Comprueba si la oferta nueva se trata de una práctica o de una oferta de empleo y
     * muestra u oculta los campos correspondientes
     */
    public void onChangeSpinnerTypeOffer(View view) {
        SwitchCompat swTypeOffer = findViewById(R.id.swTypeOffer);
        EditText etHours = findViewById(R.id.etHours);
        Spinner spTypeOfContract = findViewById(R.id.spTypeOfContract);
        if (swTypeOffer.isChecked()) {
            etHours.setVisibility(View.INVISIBLE);
            spTypeOfContract.setVisibility(View.VISIBLE);
        } else {
            etHours.setVisibility(View.VISIBLE);
            spTypeOfContract.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Guarda en ficheros la oferta que se ha creado
     */
    public void saveNewOffer(View view) {
        try {
            Manage mg = new Manage();
            Offer o = getElements();
            mg.addOffer(this, o, UserView.user);
            UserView.user.addOffer(o.getOfferId());
        } catch (Exception e) {
        }
        finish();
    }

    /**
     * Convierte el texto introducido en la pantalla en un objeto de tipo Practice o JobOffer
     * para poder guardarlo más tarde en los ficheros
     *
     * @return el objeto Offer
     */
    private Offer getElements() {
        String name = etName.getText().toString();
        String company = etCompany.getText().toString();
        int typeOfWork = spTypeOfWork.getSelectedItemPosition();
        int salary = Integer.parseInt(etSalary.getText().toString());
        boolean active = swActive.isChecked();
        String description = etDescription.getText().toString();
        if (isPractice()) {
            int hours = Integer.parseInt(etHours.getText().toString());
            return new Practice(company, name, salary, active, description, typeOfWork, hours);
        } else {
            int typeOfContract = spTypeOfContract.getSelectedItemPosition();
            return new JobOffer(company, name, salary, active, description, typeOfWork, typeOfContract);
        }
    }

    /**
     * Comprueba si es una oferta de trabajo o una práctica
     *
     * @return true if is a job offer
     */
    private boolean isPractice() {
        return !swTypeOffer.isChecked();
    }

    public void showOffer(Offer o) {
        if (o != null) {
            etName.setText(o.getOfferName());
            etCompany.setText(o.getCompanyName());
            spTypeOfWork.setSelection(o.getiTypeOfWork());
            etSalary.setText(String.valueOf(o.getSalary()));
            swActive.setChecked(o.isActiveOffer());
            etDescription.setText(o.getDescription());
            if (o.getClass().equals(Practice.class)) {
                // ES UNA PRACTiCA
                swTypeOffer.setChecked(false);
                etHours.setVisibility(View.VISIBLE);
                spTypeOfContract.setVisibility(View.INVISIBLE);
                etHours.setText(String.valueOf(((Practice) o).getHours()));
            } else {
                // JOB OFFER
                swTypeOffer.setChecked(true);
                etHours.setVisibility(View.INVISIBLE);
                spTypeOfContract.setVisibility(View.VISIBLE);
                spTypeOfContract.setSelection(o.getiTypeOfWork());
            }
        }
    }

    private void setStatusLayout(Boolean aBoolean) {
        layContent.setVisibility(View.VISIBLE);
        layModal.setVisibility(View.INVISIBLE);
        btnEdit.setVisibility(View.INVISIBLE);
        btnRemove.setVisibility(View.INVISIBLE);
        if (aBoolean) {
            // NUEVA OFERTA O MODIFICAR
            etName.setEnabled(true);
            etCompany.setEnabled(true);
            spTypeOfWork.setEnabled(true);
            etSalary.setEnabled(true);
            swActive.setEnabled(true);
            etHours.setEnabled(true);
            etDescription.setEnabled(true);
            swTypeOffer.setEnabled(true);
            spTypeOfContract.setEnabled(true);
            btnSave.setVisibility(View.VISIBLE);
            btnClose.setVisibility(View.INVISIBLE);
            btnEdit.setVisibility(View.INVISIBLE);
            btnRemove.setVisibility(View.INVISIBLE);
            btnInscrib.setVisibility(View.INVISIBLE);
            users.setVisibility(View.INVISIBLE);
        } else {
            // SOLO VISIONAR OFERTA
            etName.setEnabled(false);
            etCompany.setEnabled(false);
            spTypeOfWork.setEnabled(false);
            etSalary.setEnabled(false);
            swActive.setEnabled(false);
            etHours.setEnabled(false);
            etDescription.setEnabled(false);
            swTypeOffer.setEnabled(false);
            spTypeOfContract.setEnabled(false);
            btnSave.setVisibility(View.INVISIBLE);
            btnClose.setVisibility(View.VISIBLE);
            users.setVisibility(View.INVISIBLE);
            if (isOwner()) {
                btnEdit.setVisibility(View.VISIBLE);
                btnEditSave.setVisibility(View.VISIBLE);
                btnRemove.setVisibility(View.VISIBLE);
                btnInscrib.setVisibility(View.INVISIBLE);
                users.setVisibility(View.VISIBLE);
            } else {
                btnEdit.setVisibility(View.INVISIBLE);
                btnEditSave.setVisibility(View.INVISIBLE);
                btnRemove.setVisibility(View.INVISIBLE);
                if (o.isSuscribed(UserView.user.getEmail())) {
                    // Ocultar botón suscribirse en caso de que ya lo esté
                    btnInscrib.setVisibility(View.INVISIBLE);
                } else {
                    btnInscrib.setVisibility(View.VISIBLE);
                }
            }
        }
        btnEditSave.setVisibility(View.INVISIBLE);
    }


    public void close(View v) {
        super.onBackPressed();
    }

    public void modify(View view) {
        setStatusLayout(true);
        txtTitulo.setText(R.string.modify_offer);
        btnSave.setVisibility(View.INVISIBLE);
        btnEditSave.setVisibility(View.VISIBLE);
    }

    private boolean isOwner() {
        return UserView.user.getEmail().toString().equals(this.lOffers.get(position).getOwner());
    }

    public void saveEditedOffer(View view) {
        Offer o = modifyOffer();
        Manage manage = new Manage();
        manage.delete(this, o);
        //manage.updateOffers(this, lOffers);
        manage.addOffer(this, o, UserView.user);
        finish();
    }

    private Offer modifyOffer() {
        // MODIFICO OFERTA DE LA POSICION POSITION
        return getElements();
        //this.lOffers.set(position, o);
    }

    /**
     * Operación para inscribirse a una oferta.
     */
    public void joinOffer(View view) {
        //Debo ocultar el resto de botones
        //setStatusLayout(true);
        o.addSub(UserView.user.getEmail());
        ;
        Manage manage = new Manage();
        manage.updateOffers(this, o);
        btnInscrib.setVisibility(View.INVISIBLE);
    }

    public void removeOffer(View view) {
        layContent.setVisibility(View.INVISIBLE);
        layModal.setVisibility(View.VISIBLE);
    }

    public void removeAccept(View view) {
        deleteOffer(true);
    }

    public void removeReject(View view) {
        deleteOffer(false);
    }

    private void deleteOffer(boolean bool) {
        if (bool) {
            Manage manage = new Manage();
            manage.delete(this, o);
            finish();
        }
        layContent.setVisibility(View.VISIBLE);
        layModal.setVisibility(View.INVISIBLE);
    }

    /**
     * Ponerlo en cada clase que requiera del botón de ayuda.
     * Editar el botón para que sea mas vistoso.
     *
     * @param view
     */
    public void manual(View view) {
        Intent intent = new Intent(this, ManualActivity.class);
        startActivity(intent);
    }

    public void showUsers(View view) {

        Intent i = new Intent(this, Inscriptions.class);
        Bundle b = new Bundle();
        //b.putSerializable("list", (Serializable) o.getInscrp());
        b.putSerializable("offer", o);
        //b.putInt("offerID", o.getOfferId());
        startActivity(i.putExtras(b));
        finish();
    }
}