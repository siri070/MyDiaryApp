package com.example.irisb.mydiaryapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.app.NotificationManager;
import android.app.RemoteInput;
import android.app.PendingIntent;
import android.os.Build;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class NotificationReceiver extends BroadcastReceiver {
    public static final String KEY_NOTIFICATION_REPLY = "KEY_NOTIFICATION_REPLY";
    private static final int NOTIFICATION_ID = 0x78;
    String terminTitel;
    String terminBemerkung;
    public NotificationReceiver(String tt,String tb) {
        terminTitel=tt;
        terminBemerkung=tb;
    }

    public NotificationReceiver(){

    }
    public void onReceive(Context context, Intent intent) {try {
               Intent it =  new Intent(context, MainActivity.class);
            createNotification(context, it);

    }catch (Exception e){


    }


    }

        public void createNotification(Context context, Intent intent){

            //TODO muss noch auf To-Do-Activity zeigen
            Intent detailsIntent = new Intent(context, MainActivity.class);
            detailsIntent.putExtra("EXTRA_DETAILS_ID", 42);
            PendingIntent detailsPendingIntent = PendingIntent.getActivity(
                    context,
                    0,
                    detailsIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );
            PendingIntent replyPendingIntent = null;
// Call Activity on platforms that don't support DirectReply natively
            if (Build.VERSION.SDK_INT < 24) {
                replyPendingIntent = detailsPendingIntent;
            } else { // Call BroadcastReceiver on platforms supporting DirectReply
                replyPendingIntent = PendingIntent.getBroadcast(
                        context,
                        0,
                        new Intent(context, NotificationReceiver.class),
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
            }

            // NotificationCompat Builder takes care of backwards compatibility and
            // provides clean API to create rich notifications
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setContentTitle(terminTitel)
                    .setContentText(terminBemerkung)
                    .setAutoCancel(true)
                    .setContentIntent(detailsPendingIntent)
                    ;

            int notificationId= 1;
            // Obtain NotificationManager system service in order to show the notification
            NotificationManager notificationManager = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
            notificationManager.notify(notificationId, mBuilder.build());
    }
}
