package jp.eda_inc.samplenotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    enum EnNotification {
        VIBRATION, SOUND, ALL, LIGHT
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_sound).setOnClickListener(this);
        findViewById(R.id.button_vibration).setOnClickListener(this);
        findViewById(R.id.button_all).setOnClickListener(this);
        findViewById(R.id.button_light).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_sound:
                showNotification(EnNotification.SOUND);
                break;
            case R.id.button_vibration:
                showNotification(EnNotification.VIBRATION);
                break;
            case R.id.button_all:
                showNotification(EnNotification.ALL);
                break;
            case R.id.button_light:
                showNotification(EnNotification.LIGHT);
                break;
        }
    }

    private void showNotification(EnNotification notification) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        switch (notification) {

            case VIBRATION:
                builder.setContentTitle("VIBRATION");
                builder.setDefaults(Notification.DEFAULT_VIBRATE);
                break;
            case SOUND:
                builder.setContentTitle("SOUND");
                builder.setDefaults(Notification.DEFAULT_SOUND);
                break;
            case ALL:
                builder.setContentTitle("ALL");
                builder.setDefaults(Notification.DEFAULT_ALL);
                break;
            case LIGHT:
                builder.setContentTitle("LIGHT");
                builder.setDefaults(Notification.DEFAULT_LIGHTS);
                break;
        }
        builder.setWhen(System.currentTimeMillis());
        NotificationManager manager = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
