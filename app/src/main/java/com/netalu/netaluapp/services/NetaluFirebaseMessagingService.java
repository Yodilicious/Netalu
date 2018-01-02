package com.netalu.netaluapp.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.netalu.netaluapp.MainActivity;
import com.netalu.netaluapp.R;

public class NetaluFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "NetaluFirebaseMessagingService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage != null) {

            final String message = remoteMessage.getNotification().getBody();

            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 1410, intent, PendingIntent.FLAG_ONE_SHOT);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_search_black_24dp)
                    .setContentTitle("Message")
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify(1410, notificationBuilder.build());

            //final String title = remoteMessage.getNotification().getTitle();

            android.os.Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
