package com.example.irisb.mydiaryapp;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

public class TagesansichtActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tagesansicht);

        String[] event= {"Deutsch mündlich", "Talon abgabe"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getListView().getContext(), android.R.layout.list_content, event);
        getListView().setAdapter(adapter);
    }

}
