package ru.sash0k.bluetooth_terminal.mobi.core.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import ru.sash0k.bluetooth_terminal.mobi.core.NotificationCreator;
import ru.sash0k.bluetooth_terminal.mobi.model.Const;
import ru.sash0k.bluetooth_terminal.mobi.model.Enums;

public class NotificationsService extends Service {

    private NotificationManager manager;
    private NotificationCreator notificationReceiver;
    private IntentFilter filter;

    @Override
    public void onCreate() {
        super.onCreate();
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationReceiver = new NotificationCreator();
        filter = new IntentFilter();
        filter.addAction(Const.NEW_MESSAGE_ACTION);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        registerReceiver(notificationReceiver, filter);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {

    }
}
