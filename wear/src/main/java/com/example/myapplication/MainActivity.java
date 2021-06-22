package com.example.myapplication;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Notification.Style;
import android.app.Notification.InboxStyle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends Activity {

    private Button wBoton,button;
    private ActivityMainBinding binding;
    private Intent intent;
    private PendingIntent pendingIntent;

    private NotificationCompat.Builder notificacion;
    private NotificationCompat.Builder notificacion2;
    private NotificationManagerCompat nm;
    private NotificationCompat.WearableExtender wearableExtender,wearableExtender2;

    String idChannel = "Mi_Canal";
    int idNotificacion = 001;

    private NotificationCompat.BigTextStyle bigTextStyle;

    String longText = "Without BigTextStyle, only a single line of text would be visible. " +
            "Any additional text would not appear directly on the notification. " +
            "The entire first line would not even be on the notification if it were too long! " +
            "Text that doesn't fit in a standard notification becomes ellipsized. " +
            "That is, the characters that don't fit are removed and replaced by ellipsis.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        wBoton = (Button) findViewById(R.id.wBoton);
        button = (Button) findViewById(R.id.click);

        intent = new Intent(MainActivity.this, MainActivity.class);

        nm = NotificationManagerCompat.from(MainActivity.this);

        wearableExtender = new NotificationCompat.WearableExtender();


        bigTextStyle = new NotificationCompat.BigTextStyle().bigText(longText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
                inboxStyle.setBigContentTitle("Inbox Style Notification");
                inboxStyle.addLine("Mensaje 1");
                inboxStyle.addLine("Mensaje 2");
                inboxStyle.addLine("Mensaje 3");
                inboxStyle.addLine("Mensaje 4");
                inboxStyle.setSummaryText("+2 more");

                int importance = NotificationManager.IMPORTANCE_HIGH;
                String name = "Notificación";

                NotificationChannel notificationChannel =
                        new NotificationChannel(idChannel, name, importance);

                notificationChannel.enableVibration(true);

                nm.createNotificationChannel(notificationChannel);

                pendingIntent =
                        PendingIntent.getActivity(MainActivity.this,
                                0,
                                intent,
                                PendingIntent.FLAG_UPDATE_CURRENT);


                notificacion.setSmallIcon(R.mipmap.ic_launcher);
                notificacion.setTicker("Nueva notificación");
                notificacion.setWhen(System.currentTimeMillis());
                notificacion.setContentTitle("Título");
                notificacion.setContentText("Notificación de prueba");
                notificacion.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                notificacion.setAutoCancel(true);
                notificacion.setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                notificacion.setContentIntent(pendingIntent);
                notificacion.setStyle(inboxStyle);
                notificacion.addAction(R.mipmap.ic_launcher, "Reply",
                        pendingIntent);
                notificacion.extend(wearableExtender.addPage(notificacion.build()));


                nm.notify(idNotificacion, notificacion.build());






            }
        });

        wBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}