package com.example.irisb.mydiaryapp;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class TagesansichtActivity extends AppCompatActivity {
    ArrayAdapter eventListe;
    ArrayAdapter<String> terminListe;
    ArrayList<String> terminListe2 = new ArrayList<String>();
    String date;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tagesansicht);
       // addEventToList();
        Intent intent = getIntent();
        date= intent.getStringExtra("date");
        onClickAdd();
        addTerminToList();
    }
    private void addTerminToList(){
        ListView termine = (ListView) findViewById(R.id.termine);
        terminListe = new ArrayAdapter<String>(this, android.R.layout. simple_list_item_1 );
        TerminData data= new TerminData(getApplicationContext());
        final ArrayList<ArrayList<String>> alleTermine = data.data();
        for (ArrayList<String> b : alleTermine ) {
            if(b.size()==4){

                    String terminTitel =b.get(0) ;
                    String terminBemerkung = b.get(1);
                    String datum = b.get(2);
                    String dateSelected= date.toString();
                   if (dateSelected.contains(datum)) {
                    terminListe.add(terminTitel+"  "+terminBemerkung);
                    terminListe2.add(b.get(3));
                }

            }

        }
       termine.setAdapter(terminListe);

    }
    private void addEventToList() {
        @SuppressLint("WrongViewCast") ListView event = (ListView) findViewById(R.id.terminHinzu);
        eventListe = new ArrayAdapter<String>(this, android.R.layout. simple_list_item_1 );
        final ArrayList<ArrayList<String>> AlleTermine = TerminData.AlleTermine(getApplicationContext());
        for (ArrayList<String> b : AlleTermine) {
            eventListe.add(b.get(2)+"-"+b.get(3));
        }
        event.setAdapter(eventListe);

        AdapterView.OnItemClickListener mListClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), TagesansichtActivity.class);
                String seleced = parent.getItemAtPosition(position).toString();
                Toast.makeText(TagesansichtActivity.this, seleced, Toast.LENGTH_SHORT).show();
                //Intent mit Zusatzinformationen - hier die Badi Nummer
                intent.putExtra("titel", AlleTermine.get(position).get(0));
                intent.putExtra("notizen", seleced);
                startActivity(intent);
            }
        };
        event.setOnItemClickListener(mListClickedHandler);
    }

    private void onClickAdd(){
        Button terminHinzu = (Button) findViewById(R.id.terminHinzu);
        View.OnClickListener speichernListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), terminHinzufuegen.class);
                startActivity(intent);
            }
        };
        terminHinzu.setOnClickListener(speichernListener);
    }
}