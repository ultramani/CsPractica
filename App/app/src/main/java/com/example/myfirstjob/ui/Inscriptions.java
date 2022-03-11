package com.example.myfirstjob.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstjob.R;
import com.example.myfirstjob.data.Offer;
import com.example.myfirstjob.data.User;
import com.example.myfirstjob.persistence.Manage;

import java.util.LinkedList;

public class Inscriptions extends AppCompatActivity {

    private LinkedList<String> userList;
    private Offer o;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscriptions);
        Bundle bundle = getIntent().getExtras();
        o = (Offer) bundle.get("offer");
        updateList();
    }

    private void updateList() {
        userList = (LinkedList<String>) o.getInscrp();
        //userList = (LinkedList<String>) bundle.get("list");


        LinkedList<String> usersList = new LinkedList<>();
        ListView listView = findViewById(R.id.listView);
        if (userList == null)
            userList = new LinkedList<>();
        Manage m = new Manage();
        for (String user : userList) {
            User us = m.searchUserByID(user, this);
            if (us != null) {
                usersList.add("Nombre: " + us.getName() + '\n' + "Estudios: " + us.getStudies() + "\n" + "DNI: " + us.getDni() + '\n');
                ArrayAdapter lAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, usersList);
                listView.setAdapter(lAdapter);
            }
        }
        if (usersList.size() != 0) {
            listView.setClickable(true);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    showUser(position);
                }
            });
        } else {
          usersList.add("No hay ningún usuario inscrito");
            ArrayAdapter lAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, usersList);
            listView.setAdapter(lAdapter);
            listView.setClickable(false);
        }
    }

    public void close(View v) {
        super.onBackPressed();
    }

    private void showUser(int position) {
        Intent intent = new Intent(this, ShowUserProfile.class);
        Bundle bundle = new Bundle();
        bundle.putString("userEmail", this.userList.get(position));
        bundle.putInt("offerID", this.o.getOfferId());
        bundle.putString("offerName", o.getOfferName());
        startActivity(intent.putExtras(bundle));
        finish();
    }
}