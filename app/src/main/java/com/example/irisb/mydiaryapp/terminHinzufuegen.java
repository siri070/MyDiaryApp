package com.example.irisb.mydiaryapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.NavUtils;
import android.support.v4.app.NotificationCompat;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.AlarmManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;


import java.util.GregorianCalendar;
import android.support.v4.app.RemoteInput;
import android.support.v4.app.NotificationManagerCompat;

import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.StringTokenizer;
import java.util.Date;


public class terminHinzufuegen extends AppCompatActivity{
    private static String TAG = "Termin";
    public String datum;
    private int MY_PERMISSON_REQUEST_WRITE_EXTERNAL_STORAGE;
    private int MY_PERMISSON_REQUEST_READ_EXTERNAL_STORAGE;
    public static final String KEY_NOTIFICATION_REPLY = "KEY_NOTIFICATION_REPLY";
    private boolean erinnerung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termin_hinzufuegen);
        SwitchListener();
        OnClick_Speichern();
        OnClick_Abbrechen();
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
      Notification notifiy= new Notification(getApplicationContext());
    }
    private void OnClick_Abbrechen(){
        Button speichern = (Button) findViewById(R.id.abbrechen);
        View.OnClickListener speichernListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                navigateUpTo(intent);
            }
        };
        speichern.setOnClickListener(speichernListener);
    }
    private void OnClick_Speichern(){
        Button speichern = (Button) findViewById(R.id.speichern);
        View.OnClickListener speichernListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
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

    }
    private String GetData(){
        //terminTitel EditText
        EditText etTitel = (EditText) findViewById(R.id.terminTitel);
        String terminTitel = etTitel.getText().toString();

        //DatePicker terminDatum
        DatePicker dpDatum= (DatePicker)findViewById(R.id.terminDatum);
        int day = dpDatum.getDayOfMonth();
        int month = dpDatum.getMonth()+1;
        int year =  dpDatum.getYear();
        String Datum = day+"/"+month+"/"+year;
        datum=year+"-"+month+"-"+day;
        //EditText terminBemerkung
        EditText etBemerkung= (EditText) findViewById(R.id.terminBemerkung);
        String terminBemerkung = etBemerkung.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(new Date());

        StringTokenizer tokens = new StringTokenizer(date, "/");

        int thisDay= Integer.parseInt(tokens.nextToken()) ;
        int thisMonth = Integer.parseInt(tokens.nextToken()) ;
        int thisYear = Integer.parseInt(tokens.nextToken()) ;
        //Switch erinnerung

        if(erinnerung ) {




        }
        String alleInfos= terminTitel+";"+terminBemerkung+";"+Datum+";"+erinnerung;
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

    @Override
    protected void onStop() {

        super.onStop();
    }
}
