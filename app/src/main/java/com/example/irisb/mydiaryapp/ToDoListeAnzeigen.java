package com.example.irisb.mydiaryapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParsePosition;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ToDoListeAnzeigen extends AppCompatActivity {
ArrayAdapter TerminList;
    ArrayAdapter<String> terminListe= null;
    String iAmHere="todo";
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_todo:
                    if(iAmHere.contains("todo")){
                        //Todo Activity TODO starten
                        Toast.makeText(ToDoListeAnzeigen.this, "Sie befinden sich bereits hier.", Toast.LENGTH_SHORT).show();

                    }

                    return true;

                case R.id.navigation_kalender:
                    if(!iAmHere.contains("kalender")){
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);

                    }
                    return true;
            }
            return false;
        }
    };

@Override
    protected void onCreate(Bundle saveInstanceState) {
    super.onCreate(saveInstanceState);
    setContentView(R.layout.activity_todoliste_anzeigen);
    addTerminToList();
    BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation2);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
}

    private void addTerminToList(){
        ListView termine = findViewById(R.id.TerminList);
        terminListe = new ArrayAdapter<String>(this, android.R.layout. simple_list_item_1 );
        TerminData data= new TerminData(getApplicationContext());
        final ArrayList<ArrayList<String>> alleTermine = data.data();
        for (ArrayList<String> b : alleTermine ) {
            if(b.size()==4){

                String terminTitel =b.get(0) ;
                String terminBemerkung = b.get(1);
                String datum = b.get(2);
                Date date= new Date();
                String[] datum1=  datum.split("/");
                Date date1 = new Date(Integer.parseInt(datum1[2]),Integer.parseInt(datum1[1])-1,Integer.parseInt(datum1[0]));

                if (date1.after(date)) {
                    terminListe.add(terminTitel + "  " + terminBemerkung+"  "+datum);

                }
            }

        }
        termine.setAdapter(terminListe);

    }


}
/* http://www.kantiriederer.ch/arbeiten/2016/2016_Gadzhylov_Bogdan.pdf */