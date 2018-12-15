package com.example.irisb.mydiaryapp;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Notification {
    private ArrayList<String> terminList;

    public Notification(Context context){
       // favoritenListe = new ArrayAdapter<String>(this, android.R.layout. simple_list_item_1 );

        getData(context);
        notifyUser(context);
    }
    private void getData(Context context){
        terminList= new ArrayList<String>();
        TerminData td = new TerminData(context);
        final ArrayList<ArrayList<String>> alleTermine = td.AlleTermine(context);
        if(!alleTermine.isEmpty()){
            for (ArrayList<String> b : alleTermine) {
               if(b.get(3).length()==4) {
                  String data=b.get(0)+";"+b.get(1)+";"+b.get(2);
                  terminList.add(data);
               }


            }
        }

    }
    private void notifyUser(Context context) {
        if (terminList != null) {
            for (int i = 0; i < terminList.size(); i++) {
                String[] data = terminList.get(i).toString().split(";");
                String terminTitel = data[0];
                String terminBemerkung = data[1];
                String datum = data[2];

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String date = sdf.format(new Date());
                if (datum.equals(date)) {
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                            .setSmallIcon(android.R.drawable.ic_dialog_info)
                            .setContentTitle(terminTitel)
                            .setContentText(terminBemerkung)
                            .setAutoCancel(true);
                    int notificationId = 1;
                    // Obtain NotificationA system service in order to show the notification
                    NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
                    notificationManager.notify(notificationId, mBuilder.build());
                }
                else{
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                            .setSmallIcon(android.R.drawable.ic_dialog_info)
                            .setContentTitle("Keine Termine")
                            .setContentText("Sie haben heute keine Termine")
                            .setAutoCancel(true);
                    int notificationId = 1;
                    // Obtain NotificationA system service in order to show the notification
                    NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
                    notificationManager.notify(notificationId, mBuilder.build());

                }
            }
        }
    else{  NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Keine Termine")
                .setContentText("Sie haben heute keine Termine")
                .setAutoCancel(true);
            int notificationId = 1;
            // Obtain NotificationA system service in order to show the notification
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
            notificationManager.notify(notificationId, mBuilder.build());
            }
        }
    }

