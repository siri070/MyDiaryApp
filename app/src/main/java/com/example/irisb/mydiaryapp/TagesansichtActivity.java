package com.example.irisb.mydiaryapp;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class TagesansichtActivity extends ListActivity {
    ArrayAdapter eventListe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tagesansicht);
       //addEventToList();
       onClickAdd();
    }


    /*private void addEventToList() {
        ListView events = (ListView) findViewById(R.id.eventListe);
        eventListe = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        final ArrayList<ArrayList<String>> allEvents = TerminData.AlleTermine(getApplicationContext());
        for (ArrayList<String> b : allEvents) {
            eventListe.add(b.get(2) + "-" + b.get(3));

        }
        events.setAdapter(eventListe);

    }*/

    private void onClickAdd(){
        Button neuerTermin = (Button) findViewById(R.id.terminHinzu);
        View.OnClickListener speichernListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), terminHinzufuegen.class);
                startActivity(intent);

            }
        };
        neuerTermin.setOnClickListener(speichernListener);
    }
}