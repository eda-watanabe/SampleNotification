# SampleNotification

## Overview
> NotificationCompatを使用して通知機能を理解する。
通知時にバイブレーション、ライト、音を用いる場合はsetDefaultsを利用する

## Sample

```
    enum EnNotification {
        VIBRATION, SOUND, ALL, LIGHT
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
```
