package com.example.irisb.mydiaryapp;

import android.Manifest;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.provider.AlarmClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;

public class terminHinzufuegen extends AppCompatActivity{
    private static String TAG = "Termin";
    public DateFormat datum;
    private int MY_PERMISSON_REQUEST_WRITE_EXTERNAL_STORAGE;
    private int MY_PERMISSON_REQUEST_READ_EXTERNAL_STORAGE;
    private boolean erinnerung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termin_hinzufuegen);
        SwitchListener();
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
    private void SwitchListener(){
        Switch mySwitch = (Switch) findViewById(R.id.erinnerung);
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    erinnerung= true;
                }
                else{
                    erinnerung= false;
                }
            }
        });

    }

    private void save(){
        //für die Erlaubnis fragen
        //Write
        int permissonCheckWrite = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if( ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            }
            else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSON_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        }

        //Read
        int permissonCheckRead = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if( ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
            }
            else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSON_REQUEST_READ_EXTERNAL_STORAGE);
            }
        }
        String Infos = GetData();
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

                   bufferedWriter.write(Infos);
                   bufferedWriter.newLine();
                   bufferedWriter.close();
               }
               catch (Exception e){
                   Log.v(TAG, e.toString());
                   error("Der Termin konnte nicht hinzugefügt werden");
               }
               error("Der Termin wurde erstellt.");
           }


    }
    private String GetData(){
        //terminTitel EditText
        EditText etTitel = (EditText) findViewById(R.id.terminTitel);
        String terminTitel = etTitel.getText().toString();

        //DatePicker terminDatum
        DatePicker dpDatum= (DatePicker)findViewById(R.id.terminDatum);
        int day = dpDatum.getDayOfMonth();
        int month = dpDatum.getMonth();
        int year =  dpDatum.getYear();
        String Datum = day+"."+month+"."+year;

        //EditText terminBemerkung
        EditText etBemerkung= (EditText) findViewById(R.id.terminBemerkung);
        String terminBemerkung = etBemerkung.getText().toString();

        //Switch erinnerung
        if(erinnerung ){

            //Alarm implementieren
        }
        String alleInfos= terminTitel+";"+terminBemerkung+";"+Datum;
        return alleInfos;
    }

    private void error(String fehlermeldung){
        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Meldung");
        helpBuilder.setMessage(fehlermeldung);
        helpBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                    }
                });

        // Remember, create doesn't show the dialog
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }
}
