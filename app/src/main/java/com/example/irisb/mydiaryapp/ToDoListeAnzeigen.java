package com.example.irisb.mydiaryapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ToDoListeAnzeigen extends AppCompatActivity {
ArrayAdapter TerminList;

@Override
    protected void onCreate(Bundle saveInstanceState) {
    super.onCreate(saveInstanceState);
    setContentView(R.layout.activity_todoliste_anzeigen);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
}




}
/* http://www.kantiriederer.ch/arbeiten/2016/2016_Gadzhylov_Bogdan.pdf */