package com.example.irisb.mydiaryapp;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;

public class terminHinzufuegen extends AppCompatActivity {
    private static String TAG = "Termin";
    public DateFormat datum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termin_hinzufuegen);


        OnClick_Speichern();
    }
    private void OnClick_Speichern(){
        //Listener für den Wetterprognose-Button
        Button speichern = (Button) findViewById(R.id.speichern);
        View.OnClickListener speichernListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        };
        speichern.setOnClickListener(speichernListener);

    }
    private void save(){
       if(permission() ){
           File fileDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"Termine");
           //èberprüfung ob es den Ordner schon gibt, falls nicht wird er erstellt
           if(!fileDir.exists()){
               try {
                   fileDir.mkdir();
               }
               catch (Exception e ){
                   Log.v(TAG, e.toString());
               }
           }
           File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"Termine"+File.separator+datum+".csv");
           //Überprüfung ob das File schon existiert, falls nicht wird es erstellt
           if(!file.exists()){
               try{
                   file.createNewFile();
               }
               catch (Exception e){
                   Log.v(TAG, e.toString());
               }
           }

           if(file.exists()){
               try{
                   FileWriter fileWriter = new FileWriter(file);
                   BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
                   //Daten in das File schreiben

                   bufferedWriter.write("");
                   bufferedWriter.close();
               }
               catch (Exception e){
                   Log.v(TAG, e.toString());
                   error("Es konnte kein neuer Favorit gesetzt werden");
               }
               error("Das hinzufügen der Badi hat geklappt: :) ");
           }
       }

    }
    private boolean permission(){
        return true;
    }
    private void error(String fehlermeldung){}
}
