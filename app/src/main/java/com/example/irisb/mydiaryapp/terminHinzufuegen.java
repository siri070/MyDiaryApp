package com.example.irisb.mydiaryapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
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

import java.io.PrintWriter;
import java.util.GregorianCalendar;
import android.support.v4.app.RemoteInput;
import android.support.v4.app.NotificationManagerCompat;
import java.io.BufferedWriter;
import java.io.File;
import android.os.Build;
import android.widget.Toast;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.StringTokenizer;
import java.util.Date;


public class terminHinzufuegen extends AppCompatActivity{
    private static String TAG = "Termin";
    public DateFormat datum;
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
        Notification notifiy= new Notification(getApplicationContext());
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
        // Machen, dases wieder zum Parent geht (Kalender oder Tages ansicht)

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
           File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"Termine"+File.separator+"TerminDaten.csv");
           //Überprüfung ob das File schon existiert, falls nicht wird es erstellt
           if(!file.exists()){
               try{
                   file.createNewFile();
                   FileWriter fileWriter = new FileWriter(file);
                 /*  BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
                   //Daten in das File schreiben

                   bufferedWriter.write(Infos);
                   bufferedWriter.newLine();
                   bufferedWriter.close();
                   error("Der Termin wurde erstellt. "+erinnerung);*/
                 PrintWriter writer = new PrintWriter(fileWriter);
                 writer.println(Infos);
                 writer.close();
               }
               catch (Exception e){
                   Log.v(TAG, e.toString());
               }
           }
            //adf
           if(file.exists()){
               try{
                   FileWriter fileWriter = new FileWriter(file);
                   /*  BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
                   //Daten in das File schreiben

                   bufferedWriter.write(Infos);
                   bufferedWriter.newLine();
                   bufferedWriter.close();
                   error("Der Termin wurde erstellt. "+erinnerung);*/
                   PrintWriter writer = new PrintWriter(fileWriter);
                   writer.println(Infos);
                   writer.close();
               }
               catch (Exception e){
                   Log.v(TAG, e.toString());
                   error("Der Termin konnte nicht hinzugefügt werden");
               }

           }


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

            // time at which alarm will be scheduled here alarm is scheduled at 1 day from current time,
            // we fetch  the current time in milliseconds and added 1 day time
            // i.e. 24*60*60*1000= 86,400,000   milliseconds in a day
            //TODO datum von heute mit dem anderen vergleichen
            int differenceDay;
            int differenceMonth;
            int differenceYear;
            int dayInMonth;
            if (thisDay <= day) {
                differenceDay = day - thisDay;
            } else {
                differenceDay = day;
            }
            if (thisMonth <= month) {
                differenceMonth = month - thisMonth;
            } else {
                differenceMonth = month - 10;
            }
            if (thisYear <= year) {
                differenceYear = year - thisYear;
            } else {
                differenceYear = year;
            }
            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    dayInMonth = 31 + differenceMonth;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    dayInMonth = 30 + differenceMonth;
                    break;
                case 2:
                    dayInMonth = 28 + differenceMonth;
                    break;
                default:
                    dayInMonth = 30;
            }
//TODO http://it-ride.blogspot.com/2010/10/android-implementing-notification.html versuchen wenn die App nicht läuft




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
       // Intent service = new Intent(this, SimpleService.class);

       // service.putExtra("saved",);
      //  startService(service);
     //   Intent activity = new Intent(this, NotificationA.class);
       // startActivity(activity);
        super.onStop();
    }
}
