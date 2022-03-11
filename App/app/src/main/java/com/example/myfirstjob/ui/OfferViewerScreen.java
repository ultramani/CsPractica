package com.example.myfirstjob.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myfirstjob.R;
import com.example.myfirstjob.data.Offer;
import com.example.myfirstjob.databinding.ActivityOfferViewerScreenBinding;
import com.example.myfirstjob.persistence.Manage;
import com.google.android.material.navigation.NavigationView;

import java.util.Arrays;
import java.util.LinkedList;

public class OfferViewerScreen extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityOfferViewerScreenBinding binding;
    private LinkedList<Offer> l;
    private LinkedList<Offer> list = new LinkedList<>();
    private SearchView svOfferFilter;
    private SearchView.OnQueryTextListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOfferViewerScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarOfferViewerScreen.toolbar);
        binding.appBarOfferViewerScreen.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cuando se pulse el botón se iniciará la página para crear una nueva oferta
                newOfferIntent();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.myOffer)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_offer_viewer_screen);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        svOfferFilter = findViewById(R.id.svOfferFilter);

        // Snackbars si te aceptan/rechazan en alguna oferta suscrita
        notifications();

        updateUIList();
        listener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return setSvOfferFilter();
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return setSvOfferFilter();
            }
        };
        setOfferFilterMethods();
    }

    public void setOfferFilterMethods() {
        svOfferFilter.setOnQueryTextListener(listener);
    }

    @Override
    public void onItemClick(AdapterView<?> adepterView, View view, int position, long l) {
        showOffer(position);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.offer_viewer_screen, menu);
        changeUIProfile();
        return true;
    }

    /**
     * Ponemos el nombre de usuario y el mail en el menú lateral
     */
    public void changeUIProfile() {
        TextView tname = (TextView) findViewById(R.id.name);
        TextView temail = (TextView) findViewById(R.id.email);
        tname.setText(UserView.user.getName().toString());
        temail.setText(UserView.user.getEmail().toString());
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_offer_viewer_screen);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /**
     * Crear nueva oferta
     */
    private void newOfferIntent() {
        Intent intent = new Intent(this, NewOffer.class);
        startActivity(intent.putExtra("editable", true));
    }

    /**
     * Muestra en pantalla la oferta especificada
     *
     * @param position posición de la oferta en la lista a mostrar
     */
    public void showOffer(int position) {
        Intent intent = new Intent(this, NewOffer.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean("editable", false);
        bundle.putSerializable("lOffers", list);
        bundle.putSerializable("int_offer", position);
        startActivity(intent.putExtras(bundle));
    }


    @Override
    public void onClick(View view) {
        updateUIList();
        updateMyUIList();
    }

    public void updateMyUIList() {
        LinkedList<String> offerList = new LinkedList<>();
        ListView listView = findViewById(R.id.listView2);
        listView.setOnItemClickListener(this);
        Manage m = new Manage();
        l = (LinkedList<Offer>) m.loadOffers(this);
        LinkedList<Offer> myList = new LinkedList<>();
        if (l == null)
            l = new LinkedList<>();
        for (Offer o : l) {
            if (o.getOwner().equals(UserView.user.getEmail())) {
                myList.add(o);
                offerList.add(o.getOfferName() + '\n' + "Empresa: " + o.getCompanyName() + "\n" + o.getDescription() + '\n' + "Salario: " + Integer.toString(o.getSalary()) + "€");
                ArrayAdapter lAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, offerList);
                listView.setAdapter(lAdapter);
            }
        }
        if (offerList.size() == 0) {
            offerList.add("No tiene ofertas publicadas");
            ArrayAdapter lAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, offerList);
            listView.setAdapter(lAdapter);
            listView.setClickable(false);
        } else {
            listView.setClickable(true);
            this.list = myList;
        }
    }

    /**
     * Método que actualiza la vista de las ofertas y filtra en caso de que se haya introducido algún filtro
     */
    public void updateUIList() {
        String query = svOfferFilter.getQuery().toString();
        LinkedList<String> offerList = new LinkedList<>();
        ListView listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        Manage m = new Manage();
        l = (LinkedList<Offer>) m.loadOffers(this);
        LinkedList<Offer> myList = new LinkedList<>();
        if (l == null)
            l = new LinkedList<>();
        if (query.equals("")) {
            // No especificado filtro
            for (Offer o : l) {
                if (!o.getOwner().equals(UserView.user.getEmail())) {
                    myList.add(o);
                    offerList.add(o.getOfferName() + '\n' + "Empresa: " + o.getCompanyName() + "\n" + o.getDescription() + '\n' + "Salario: " + Integer.toString(o.getSalary()) + "€");
                    ArrayAdapter lAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, offerList);
                    listView.setAdapter(lAdapter);
                }
            }
        } else {
            // Usando filtro
            for (Offer o : l) {
                String content[] = {o.getOfferName(), o.getCompanyName(), String.valueOf(o.getSalary()), String.valueOf(o.getiTypeOfWork()), o.getDescription()};
                if (Arrays.asList(content).contains(query) || query.equals("")) {
                    myList.add(o);
                    offerList.add(o.getOfferName() + '\n' + "Empresa: " + o.getCompanyName() + "\n" + o.getDescription() + '\n' + "Salario: " + Integer.toString(o.getSalary()) + "€");
                    ArrayAdapter lAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, offerList);
                    listView.setAdapter(lAdapter);
                }
            }
        }
        if (offerList.size() == 0) {
            offerList.add("No hay coincidencias");
            ArrayAdapter lAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, offerList);
            listView.setAdapter(lAdapter);
            listView.setClickable(false);
        } else {
            listView.setClickable(true);
            this.list = myList;
        }

    }

    public void manual(View view) {
        Intent intent = new Intent(this, ManualActivity.class);
        startActivity(intent);
    }

    public boolean setSvOfferFilter() {
        updateUIList();
        return true;
    }

    public void notifications() {
        LinkedList<String> suscrList;
        Manage m = new Manage();
        l = (LinkedList<Offer>) m.loadOffers(this);

        if (l == null)
            l = new LinkedList<>();

        for (Offer o : l) {
            if (!o.getOwner().equals(UserView.user.getEmail())) {
                suscrList = o.getInscrp_aproved();
                for (String e : suscrList) {
                    if (e.equals(UserView.user.getEmail())) {
                        AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
                        myAlert.setMessage("has sido aceptado en " + o.getOfferName()).setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                                .setTitle("Notificación")
                                .create();
                        myAlert.show();
                        ;
                    }
                }
            }
        }

    }

}

