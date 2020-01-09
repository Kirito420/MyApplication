package com.niknovak.sevenweekmurphworkoutchallenge;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Build;

public class App extends Application {
    public static String CHANNEL_ID = "channel";

    public void createNotificationChannels(){
        if(Build.VERSION.SDK_INT >= 26){
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Workout reminder",
                    NotificationManager.IMPORTANCE_HIGH
            );
            //channel.setSound();
            channel.enableLights(true);
            channel.setLightColor(Color.BLUE);
            channel.enableVibration(true);
            channel.setImportance(NotificationManager.IMPORTANCE_HIGH);
            channel.enableVibration(true);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
    @Override
    public void onCreate(){
        super.onCreate();
        createNotificationChannels();
    }
}
