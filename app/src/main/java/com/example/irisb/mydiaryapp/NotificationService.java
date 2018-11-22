package com.example.irisb.mydiaryapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.app.NotificationManager;

import java.text.SimpleDateFormat;
import java.util.*;

public class NotificationService extends Service{
    @Nullable
    Timer timer;
    TimerTask timerTask;
    String TAG = "Timers";
    int Your_X_SECS = 5;


    @Override
    public IBinder onBind(Intent arg0)
    {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.e(TAG, "onStartCommand");
        super.onStartCommand(intent, flags, startId);

        startTimer();

        return START_STICKY;
    }


    @Override
    public void onCreate(){
        Log.e(TAG, "onCreate");


    }

    @Override
    public void onDestroy(){
        Log.e(TAG, "onDestroy");
        stoptimertask();
        super.onDestroy();


    }

    //we are going to use a handler to be able to run in our TimerTask
    final Handler handler = new Handler();


    public  void startTimer() {
        //set a new Timer
        timer = new Timer();

        //initialize the TimerTask's job
        initializeTimerTask();

        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
        timer.schedule(timerTask, 5000, Your_X_SECS*1000); //
        //timer.schedule(timerTask, 5000,1000); //
    }

    public void stoptimertask() {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void initializeTimerTask() {

        timerTask = new TimerTask() {
            public void run() {

                //use a handler to run a toast that shows the current timestamp
                handler.post(new Runnable() {
                    public void run() {
                        final ArrayList<ArrayList<String>> allTermins= TerminData.AlleTermine(getApplicationContext());
                        for (ArrayList<String> t : allTermins) {
                          //  favoritenListe.add(b.get(2)+"-"+b.get(3));
                            // TODO CALL NOTIFICATION FUNC
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            String date = sdf.format(new Date());

                            if(Boolean.parseBoolean(t.get(3))== true){

                                if(t.get(2)==date.toString()){
                                   /* NotificationReceiver notificationReceiver = new NotificationReceiver(t.get(0),t.get(1));
                                    Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                                    notificationReceiver.onReceive(getApplicationContext(),intent);*/
                                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext())
                                            .setSmallIcon(android.R.drawable.ic_dialog_info)
                                            .setContentTitle(t.get(0))
                                            .setContentText(t.get(1))
                                            .setAutoCancel(true);
                                                                                       ;

                                    int notificationId= 1;
                                    // Obtain NotificationManager system service in order to show the notification
                                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                                    notificationManager.notify(notificationId, mBuilder.build());
                                }

                            }

                        }


                    }
                });
            }
        };
    }
}
