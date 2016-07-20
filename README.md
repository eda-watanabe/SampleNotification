# 【Android】通知をカスタマイズしよう

## Overview
アプリがバックグラウンドにあったり、起動していない時にユーザーに伝えたい情報があることもあるでしょう。Android端末ではそういった要望を叶えるために、[Notification](https://developer.android.com/reference/android/support/v4/app/NotificationCompat.Builder.html)という機能を使用して通知し、実装を行います。プッシュ通知における表示方法として、馴染みがあるかもしれませんが、プッシュ通知以外でも起動時にバックグラウンドで処理を実行しておいて、時間が経ったら通知を行う、といったことも可能なので、使い方を工夫することでユーザーがアプリを使う機会を増やすことができるでしょう。

今回はそんな便利な通知をただ表示するだけではなく、スタイルやイベント、バイブレーションやサウンドをといったカスタマイズをしてみましょう。

## 一般的な通知方法
まずは一般的な通知の実装方法です。

```
private void normalNotification() {
    NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
    builder.setSmallIcon(R.mipmap.ic_launcher);
    builder.setContentTitle("タイトル");
    builder.setContentText("内容");
    builder.setContentInfo("情報欄");
    builder.setTicker("通知概要");
    NotificationManager manager =
            (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
    manager.notify(0, builder.build());
}
```

## タップしてアプリを起動する
通知をタップしてアプリを起動することができるとよりユーザーにとって使い勝手のいいアプリとなります。タップを契機のアプリを起動したい場合の実装方法です。

```
private void launcherAppNotification() {
    NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
    builder.setSmallIcon(R.mipmap.ic_launcher);
    builder.setContentTitle("タップでアプリを起動する");

    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
    PendingIntent pendingIntent = PendingIntent.getActivity(
            mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    builder.setContentIntent(pendingIntent);
    builder.setAutoCancel(true);
    NotificationManager manager = (NotificationManager)
            getSystemService(Service.NOTIFICATION_SERVICE);
    manager.notify(0, builder.build());
}
```

## 通知オプションを設定する
通知する際にバイブレーションやOSデフォルトのサウンドを鳴らしたい場合の実装方法です。

```
private void setDefaultsNotification(EnNotification notification) {
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
    }
    builder.setWhen(System.currentTimeMillis());
    NotificationManager manager = (NotificationManager)
            getSystemService(Service.NOTIFICATION_SERVICE);
    manager.notify(0, builder.build());
}
```

## スタイルを設定する
表示領域に余裕がある場合はスタイルを設定することで通知を目立たせることができます。しかし、主張の強い通知はユーザーに嫌われることもありますので、そのスタイルがアプリの機能にマッチしているか検討することをお勧めします。PictureStyleでは写真を主に扱うアプリで用いられているのを見かけますね。

```
private void bigTextStyleNotification() {
    NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
    builder.setSmallIcon(R.mipmap.ic_launcher);
    // スタイル設定
    NotificationCompat.BigTextStyle bigTextStyle =
            new NotificationCompat.BigTextStyle(builder);
    bigTextStyle.setBigContentTitle("BigTextStyle");
    bigTextStyle.bigText("コンテンツサンプルコンテンツサンプル");
    bigTextStyle.setSummaryText("通知サマリテキスト");

    NotificationManager manager = (NotificationManager)
            getSystemService(Service.NOTIFICATION_SERVICE);
    manager.notify(0, builder.build());
}
```

```

private void pictureStyleNotification() {
    NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
    builder.setSmallIcon(R.mipmap.ic_launcher);
    // スタイル設定
    NotificationCompat.BigPictureStyle bigPictureStyle =
            new NotificationCompat.BigPictureStyle(builder);
    Bitmap largePicture = BitmapFactory.decodeResource(
            getResources(), R.mipmap.ic_launcher);
    bigPictureStyle.bigPicture(largePicture);
    bigPictureStyle.setBigContentTitle("BigPictureStyle");
    bigPictureStyle.setSummaryText("通知サマリテキスト");

    NotificationManager manager = (NotificationManager)
            getSystemService(Service.NOTIFICATION_SERVICE);
    manager.notify(0, builder.build());
}
```

